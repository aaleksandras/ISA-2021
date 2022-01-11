package com.example.backend.service.impl;

import com.example.backend.email.EmailSender;
import com.example.backend.enums.TypeOfUser;
import com.example.backend.model.user.*;
import com.example.backend.repository.*;
import com.example.backend.service.IUserService;
import com.example.backend.web.dto.CreateUserDto;
import com.example.backend.web.mapper.place.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User createUser(CreateUserDto dto) {
        switch (dto.getTypeOfUser()) {
            case "Client":
                Client client = (Client) mapDtoToUser(new Client(), dto, TypeOfUser.CLIENT, "ROLE_CLIENT");
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
        CreateUserDto dto = userMapper.fromUserToDto(userRepository.findUserById(id));
        return dto;
    }




}
