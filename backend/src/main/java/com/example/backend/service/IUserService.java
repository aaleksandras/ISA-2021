package com.example.backend.service;

import com.example.backend.model.user.User;
import com.example.backend.web.dto.CreateUserDto;
import com.example.backend.web.dto.NewPenaltyDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface IUserService extends UserDetailsService {

    User createUser(CreateUserDto dto);
    User updateStatus(UUID id);
    User updateUser(UUID id ,CreateUserDto dto);
    CreateUserDto getById(UUID id);
    public void addPenaltyToUser(NewPenaltyDTO dto);
    public void changePenaltyStatus(String status, UUID id);
}
