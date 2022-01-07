package com.example.backend.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {
    String address;
    String city;
    String country;
    String email;
    String firstName;
    String lastName;
    String password;
    String phoneNumber;
    String typeOfUser;
    String description;
}
