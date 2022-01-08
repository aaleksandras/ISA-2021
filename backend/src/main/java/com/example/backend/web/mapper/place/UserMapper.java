package com.example.backend.web.mapper.place;

import com.example.backend.model.user.User;
import com.example.backend.web.dto.CreateUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDtoToUser(User user, CreateUserDto dto){
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
    public CreateUserDto fromUserToDto(User user){
        if (user == null) return  null;
        CreateUserDto dto = new CreateUserDto();
        dto.setAddress(user.getAddress());
        dto.setCountry(user.getCountry());
        dto.setCity(user.getCity());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
