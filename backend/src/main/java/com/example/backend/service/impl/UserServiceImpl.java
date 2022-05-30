package com.example.backend.service.impl;

import com.example.backend.email.EmailSender;
import com.example.backend.enums.LoyaltyCategory;
import com.example.backend.enums.StatusOfPenalty;
import com.example.backend.enums.TypeOfUser;
import com.example.backend.model.penalty.Penalty;
import com.example.backend.model.reservation.Reservation;
import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.model.reservation.Revision;
import com.example.backend.model.user.*;
import com.example.backend.repository.*;
import com.example.backend.service.IUserService;
import com.example.backend.web.dto.CommentsDTO;
import com.example.backend.web.dto.CreateUserDto;
import com.example.backend.web.dto.LoyaltyProgramDTO;
import com.example.backend.web.dto.NewPenaltyDTO;
import com.example.backend.web.mapper.place.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private HouseOwnerRepository houseOwnerRepository;
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private PenaltyRepository penaltyRepository;

    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationEntityRepository reservationEntityRepository;

    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User createUser(CreateUserDto dto) {
        switch (dto.getTypeOfUser()) {
            case "Client":
                Client client = (Client) mapDtoToUser(new Client(), dto, TypeOfUser.CLIENT, "ROLE_CLIENT");
                client.setPoints(0);
                client.setLoyaltyCategory(LoyaltyCategory.NONE);
                clientRepository.save(client);
                return client;
            case "Boat Owner":
                BoatOwner boatOwner = (BoatOwner) mapDtoToUser(new BoatOwner(), dto, TypeOfUser.BOATOWNER, "ROLE_BOAT_OWNER");
                boatOwner.setDescription(dto.getDescription());
                boatOwnerRepository.save(boatOwner);
                return boatOwner;
            case "House Owner":
                HouseOwner houseOwner = (HouseOwner) mapDtoToUser(new HouseOwner(), dto, TypeOfUser.HOUSEOWNER, "ROLE_HOUSE_OWNER");
                houseOwner.setDescription(dto.getDescription());
                houseOwnerRepository.save(houseOwner);
                return houseOwner;
            case "Instructor":
                Instructor instructor = (Instructor) mapDtoToUser(new Instructor(), dto, TypeOfUser.INSTRUCTOR, "ROLE_INSTRUCTOR");
                instructor.setDescription(dto.getDescription());
                instructorRepository.save(instructor);
                return instructor;
            case "Administrator":
                Administrator admin = (Administrator) mapDtoToUser(new Administrator(), dto, TypeOfUser.ADMINISTRATOR, "ROLE_ADMINISTRATOR");
                admin.setFirstLogin(true);
                admin.setIsActive(true);
                administratorRepository.save(admin);
                return admin;
        }
        return null;
    }

    private User mapDtoToUser(User user, CreateUserDto dto, TypeOfUser typeOfUser, String roleName) {
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setTypeOfUser(typeOfUser);
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRoles(getRoleForUser(roleName));
        user.setUsername(dto.getEmail());
        user.setIsActive(false);
        user.setIsDeclined(false);
        return user;
    }

    private Set<Role> getRoleForUser(String roleName) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByName(roleName));
        return roles;
    }

    @Override
    public User updateStatus(UUID id) {
        User user = this.userRepository.getById(id);
        user.setIsActive(true);
        if (user.getTypeOfUser() != TypeOfUser.ADMINISTRATOR && user.getTypeOfUser() != TypeOfUser.CLIENT) {
            try {
                emailSender.sendAcceptingEmail(user.getEmail(), user.getId().toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, CreateUserDto dto) {
        User user = this.userRepository.findUserById(id);
        user = userMapper.fromDtoToUser(user, dto);
        if (!dto.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public CreateUserDto getById(UUID id) {
        User user = userRepository.findUserById(id);
        CreateUserDto dto = userMapper.fromUserToDto(userRepository.findUserById(id));
        if(user.getTypeOfUser().equals(TypeOfUser.CLIENT)){
            Client client = clientRepository.getById(id);
            dto.setLoyaltyCategory(client.getLoyaltyCategory().toString());
            dto.setPoints(client.getPoints());
        }else{
            dto.setLoyaltyCategory("NONE");
            dto.setPoints(0);
        }

        return dto;
    }

    @Override
    public void addPenaltyToUser(NewPenaltyDTO dto) {
        Date today = new Date();
        Date nextMonth = today;
        nextMonth.setMonth(today.getMonth() + 1);
        nextMonth.setDate(1);
        Penalty penalty = new Penalty(dto, nextMonth);
        User client = userRepository.findByEmail(dto.getUsername());
        penalty.setUsername(client.getUsername());
        client.getListOfPenalties().add(penalty);
        penaltyRepository.save(penalty);
        userRepository.save(client);

    }

    @Override
    public void changePenaltyStatus(String status, UUID id) {
        Penalty penalty = penaltyRepository.getById(id);
        if (status.equals("ACCEPTED")) {
            penalty.setStatusOfPenalty(StatusOfPenalty.ACCEPTED);
        } else {
            penalty.setStatusOfPenalty(StatusOfPenalty.REJECTED);
        }
        penaltyRepository.save(penalty);
    }

    @Override
    public List<CommentsDTO> getComments(UUID id) {
        //TODO Dobaviti revizije za korisnika sa id
        List<Revision> allRevisions = revisionRepository.findAll();
        List<CommentsDTO> comments = new ArrayList<>();
        System.out.println("------------------------------------------");
        System.out.println(allRevisions);
        for(Revision r  : allRevisions){
            System.out.println("uslo je");
            comments.add(new CommentsDTO(r));
        }
        return comments;
//        List<Revision> allRevisions = revisionRepository.findAllById(Collections.singleton(id));
//        System.out.println(" xxxxx");
//        System.out.println(allRevisions.size());
//        List<CommentsDTO> comments = new ArrayList<>();
//        for(Revision r  : allRevisions){
//            comments.add(new CommentsDTO(r));
//        }
//        return comments;


//        List<Reservation> allReservations = reservationRepository.findAll();
//        List<Revision> filteredRevisions = new ArrayList<>();
//        List<ReservationEntity> =.
////        for(Reservation r : allReservations){
////            if(r.getReservation().getId()==)
////        }

    }

    @Override
    public List<NewPenaltyDTO> getPenalties() {
        List<Penalty> penalties = penaltyRepository.findAll();
        List<NewPenaltyDTO> dtos = new ArrayList<>();
        for(Penalty p:penalties)
        {
            if(p.getStatusOfPenalty().equals(StatusOfPenalty.NEW))
            {
                NewPenaltyDTO dto = new NewPenaltyDTO(p);
                dtos.add(dto);
            }
        }
        return  dtos;
    }

    @Override
    public List<NewPenaltyDTO> getMyPenalties(UUID id) {
        User u = userRepository.findUserById(id);
        List<Penalty> penalties = penaltyRepository.findAll();
        List<NewPenaltyDTO> dtos = new ArrayList<>();
        for(Penalty p:penalties)
        {
            if(p.getEndDate().after(new Date()) && p.getUsername().equals(u.getUsername()))
            {
                NewPenaltyDTO dto = new NewPenaltyDTO(p);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public void defineLoyalityProgram(LoyaltyProgramDTO dto) {
        List<LoyalityProgram> list = loyaltyProgramRepository.findAll();
        if(list.isEmpty()){
            LoyalityProgram newProgram = new LoyalityProgram();
            newProgram.setClientPointsForReservation(dto.getClientPointsForReservation());
            newProgram.setPercentRegular(dto.getPercentRegular());
            newProgram.setPercentGold(dto.getPercentGold());
            newProgram.setPercentSilver(dto.getPercentSilver());
            newProgram.setPointsToRegular(dto.getPointsToRegular());
            newProgram.setPointsToGold(dto.getPointsToGold());
            newProgram.setPointsToSilver(dto.getPointsToSilver());
            loyaltyProgramRepository.save(newProgram);
        }else{
            for (LoyalityProgram updatedProgram:list){
                updatedProgram.setClientPointsForReservation(dto.getClientPointsForReservation());
                updatedProgram.setPercentRegular(dto.getPercentRegular());
                updatedProgram.setPercentGold(dto.getPercentGold());
                updatedProgram.setPercentSilver(dto.getPercentSilver());
                updatedProgram.setPointsToRegular(dto.getPointsToRegular());
                updatedProgram.setPointsToGold(dto.getPointsToGold());
                updatedProgram.setPointsToSilver(dto.getPointsToSilver());
                loyaltyProgramRepository.save(updatedProgram);
            }
        }
    }


}
