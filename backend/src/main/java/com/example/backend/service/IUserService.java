package com.example.backend.service;

import com.example.backend.model.user.User;
import com.example.backend.web.dto.CommentsDTO;
import com.example.backend.web.dto.CreateUserDto;
import com.example.backend.web.dto.LoyaltyProgramDTO;
import com.example.backend.web.dto.NewPenaltyDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface IUserService extends UserDetailsService {

    User createUser(CreateUserDto dto);
    User updateStatus(UUID id);
    User updateUser(UUID id ,CreateUserDto dto);
    CreateUserDto getById(UUID id);
    public void addPenaltyToUser(NewPenaltyDTO dto);
    public void changePenaltyStatus(String status, UUID id);

    List<CommentsDTO> getComments(UUID id);

    List<NewPenaltyDTO> getPenalties();

    List<NewPenaltyDTO> getMyPenalties(UUID id);

    public void defineLoyalityProgram(LoyaltyProgramDTO dto);

    public void addPointsAndChangeCategory(UUID id);

    Double getNewPrice(Double price, UUID id);
}
