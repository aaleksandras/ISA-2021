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
}
