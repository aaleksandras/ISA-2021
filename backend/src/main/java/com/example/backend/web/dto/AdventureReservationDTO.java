package com.example.backend.web.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class AdventureReservationDTO {
    public UUID id;
    public String name;
    public String description;
    public Integer maxPersons;
    public String address;
    public Double price;
}
