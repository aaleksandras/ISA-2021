package com.example.backend.web.controller;

import com.example.backend.email.EmailSender;
import com.example.backend.enums.StatusOfReservation;
import com.example.backend.enums.TypeOfUser;
import com.example.backend.model.reservation.PercentageFromReservations;
import com.example.backend.model.reservation.Reservation;
import com.example.backend.model.user.Administrator;
import com.example.backend.model.user.Client;
import com.example.backend.model.user.DeleteAccountRequest;
import com.example.backend.model.user.User;
import com.example.backend.repository.*;
import com.example.backend.service.IUserService;
import com.example.backend.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    EmailSender sender;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdministratorRepository administratorRepository;
    @Autowired
    DeleteAccountRequestRepository deleteAccountRequestRepository;
    @Autowired
    PercentageFromReservationsRepository percentageFromReservationsRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ClientRepository clientRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CLIENT','ROLE_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        CreateUserDto user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        User user = userRepository.findUserById(UUID.fromString(id));
        user.setDeleted(true);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/getPercentageForReservations")
    public ResponseEntity<?> getPercentageForReservations() {
        PercentageFromReservations percentage = percentageFromReservationsRepository.findAll().get(0);
        return new ResponseEntity<>(percentage, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/changePercentage/{newPercentage}")
    public ResponseEntity<?> changePercentage(@PathVariable String newPercentage) {
        PercentageFromReservations percentage = percentageFromReservationsRepository.findAll().get(0);
        percentage.setPercentage(Double.parseDouble(newPercentage));
        percentageFromReservationsRepository.save(percentage);
        return new ResponseEntity<>(percentage, HttpStatus.OK);
    }


    @GetMapping("/requestDelete/{id}")
    public ResponseEntity<?> requestDelete(@PathVariable String id) {
        User user = userRepository.findUserById(UUID.fromString(id));
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest();
        deleteAccountRequest.setEmail(user.getEmail());
        deleteAccountRequest.setFullName(user.getFirstName() + " " + user.getLastName());
        deleteAccountRequest.setUserId(user.getId());
        deleteAccountRequest.setTypeOfUser(user.getTypeOfUser().toString());
        deleteAccountRequest.setAnswered(false);
        deleteAccountRequestRepository.save(deleteAccountRequest);
        return new ResponseEntity<>(deleteAccountRequest, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/acceptDeleteRequest")
    public ResponseEntity<?> acceptRequest(@RequestBody DeleteAccountDto dto) {
        DeleteAccountRequest deleteAccountRequest = deleteAccountRequestRepository.findById(UUID.fromString(dto.getDeleteRequestId())).get();
        User user = userRepository.findUserById(deleteAccountRequest.getUserId());
        user.setDeleted(true);
        userRepository.save(user);
        deleteAccountRequest.setAnswered(true);
        deleteAccountRequestRepository.save(deleteAccountRequest);
        sender.sendAcceptingAccountDeletionMail(user.getEmail(), dto.getMessage());
        return new ResponseEntity<>(deleteAccountRequest, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/declineDeleteRequest")
    public ResponseEntity<?> declineRequest(@RequestBody DeleteAccountDto dto) {
        DeleteAccountRequest deleteAccountRequest = deleteAccountRequestRepository.findById(UUID.fromString(dto.getDeleteRequestId())).get();
        User user = userRepository.findUserById(deleteAccountRequest.getUserId());
        deleteAccountRequest.setAnswered(true);
        deleteAccountRequestRepository.save(deleteAccountRequest);
        sender.sendDecliningAccountDeletionMail(user.getEmail(), dto.getMessage());
        return new ResponseEntity<>(deleteAccountRequest, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/getReport")
    public ResponseEntity<?> declineRequest(@RequestBody GenerateReportDto dto) {
        var reservations = reservationRepository.findAll();
        PercentageFromReservations percentage = percentageFromReservationsRepository.findAll().get(0);
        double income = 0;
        for (Reservation res : reservations) {
            if (res.getStatusOfReservation() == StatusOfReservation.SCHEDULED &&
                    res.getTerm().getStartDate().after(dto.getStartDate()) &&
                    res.getTerm().getEndDate().before(dto.getEndDate())) {

                income += percentage.getPercentage() / 100 * res.getPrice();
            }
        }
        ReportResponse response = new ReportResponse();
        response.setPercentage(percentage.getPercentage());
        response.setIncome(income);
        response.setEndDate(dto.getEndDate());
        response.setStartDate(dto.getStartDate());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/deleteRequests")
    public ResponseEntity<?> getDeleteRequests() {
        return new ResponseEntity<>(deleteAccountRequestRepository.findAll().stream()
                .filter(req -> req.isAnswered() == false), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody CreateUserDto dto) {
        User user = userService.createUser(dto);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            if (user.getTypeOfUser() == TypeOfUser.CLIENT) {
                sender.sendVerificationEmail(user.getEmail(), user.getId().toString());
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody CreateUserDto dto) {
        User user = userService.createUser(dto);
        return new ResponseEntity<>(user, user == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto dto) {
        Administrator admin = administratorRepository.findById(UUID.fromString(dto.getId())).get();
        admin.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        admin.setFirstLogin(false);
        administratorRepository.save(admin);
        return new ResponseEntity<>(admin, admin == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping("/activateAccount/{id}")
    public void activateAccount(@PathVariable String id) {
        User user = this.userService.updateStatus(UUID.fromString(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/declineAccount/{id}")
    public void declineAccount(@PathVariable String id) {
        User user = userRepository.findUserById(UUID.fromString(id));
        user.setIsDeclined(true);
        userRepository.save(user);
        sender.sendDeclineEmail(id, user.getEmail());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CLIENT','ROLE_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_INSTRUCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CreateUserDto dto) {
        User user = userService.updateUser(UUID.fromString(id), dto);
        return new ResponseEntity<>(user, user == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/getUnverified")
    public ResponseEntity<?> getUnverified() {
        return new ResponseEntity<>(
                userRepository.findAll()
                        .stream()
                        .filter(u -> u.getIsActive() == false && u.getIsDeclined() == false && u.getTypeOfUser() != TypeOfUser.CLIENT),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(
                userRepository.findAll()
                        .stream()
                        .filter(u -> u.getTypeOfUser() != TypeOfUser.ADMINISTRATOR && u.getIsActive() == true),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_BOAT_OWNER', 'ROLE_HOUSE_OWNER', 'ROLE_INSTRUCTOR')")
    @PostMapping("/addPenalty")
    public ResponseEntity<?> addPenaltyToUser(@RequestBody NewPenaltyDTO dto) {
        userService.addPenaltyToUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_BOAT_OWNER', 'ROLE_HOUSE_OWNER', 'ROLE_INSTRUCTOR')")
    @PostMapping("/addPenalty1")
    public ResponseEntity<?> addPenaltyToUser1(@RequestBody NewPenaltyDTO dto) {
        System.out.println("uslo u ovaj controller");
        userService.addPenaltyToUser1(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/del/{id}")
    public ResponseEntity<?> changePenaltyStatus(@PathVariable UUID id) {
        userService.changePenaltyStatus("a", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/acc/{id}")
    public ResponseEntity<?> changePenaltyStatusA(@PathVariable  UUID id) {
        userService.changePenaltyStatus("ACCEPTED", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_BOAT_OWNER', 'ROLE_HOUSE_OWNER', 'ROLE_INSTRUCTOR')")
    @GetMapping("/getComments/{id}")
    public List<CommentsDTO> getComments(@PathVariable UUID id) {

        return userService.getComments(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/getPenalties/{id}")
    public List<NewPenaltyDTO> getPenalties(@PathVariable UUID id) {

        return userService.getPenalties();
    }
    @GetMapping("/getMyPenalties/{id}")
    public List<NewPenaltyDTO> getMyPenalties(@PathVariable UUID id) {

        return userService.getMyPenalties(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PostMapping("/defineLoyaltyProgram")
    public ResponseEntity<?> defineProgram(@RequestBody LoyaltyProgramDTO dto) {
        userService.defineLoyalityProgram(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/getLoyaltyProgram")
    public ResponseEntity<?> getLoyaltyProgram() {
        LoyaltyProgramDTO program = userService.getLoyaltyProgram();
        return new ResponseEntity<>(program, HttpStatus.OK);
    }
    @PostMapping("/removePenalties")
    public ResponseEntity<?> removePenalties() {
        LocalDate now = LocalDate.now();
        int day= now.getDayOfMonth();

        List<Client> users = clientRepository.findAll();

        for(Client u:users) {
            if (day == 1) {
                userService.removedPenalties(u);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
