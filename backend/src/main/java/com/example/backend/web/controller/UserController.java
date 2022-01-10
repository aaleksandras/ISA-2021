package com.example.backend.web.controller;

import com.example.backend.email.EmailSender;
import com.example.backend.enums.TypeOfUser;
import com.example.backend.model.user.Administrator;
import com.example.backend.model.user.User;
import com.example.backend.repository.AdministratorRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.IUserService;
import com.example.backend.web.dto.CreateUserDto;
import com.example.backend.web.dto.ResetPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
}
