package com.example.backend.service.impl;

import com.example.backend.enums.TypeOfUser;
import com.example.backend.model.user.*;
import com.example.backend.repository.*;
import com.example.backend.service.IUserService;
import com.example.backend.web.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User createUser(CreateUserDto dto) {
        switch (dto.getTypeOfUser()) {
            case "Client":
                Client client = (Client) mapDtoToUser(new Client(), dto, TypeOfUser.CLIENT);
                clientRepository.save(client);
                return client;
            case "Boat Owner":
                BoatOwner boatOwner = (BoatOwner) mapDtoToUser(new BoatOwner(), dto, TypeOfUser.BOATOWNER);
                boatOwnerRepository.save(boatOwner);
                return boatOwner;
            case "House Owner":
                HouseOwner houseOwner = (HouseOwner) mapDtoToUser(new HouseOwner(), dto, TypeOfUser.HOUSEOWNER);
                houseOwnerRepository.save(houseOwner);
                return houseOwner;
            case "Instructor":
                Instructor instructor = (Instructor) mapDtoToUser(new Instructor(), dto, TypeOfUser.INSTRUCTOR);
                instructorRepository.save(instructor);
                return instructor;
        }
        return null;
    }

    private User mapDtoToUser(User user, CreateUserDto dto, TypeOfUser typeOfUser) {
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setTypeOfUser(typeOfUser);
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
}
