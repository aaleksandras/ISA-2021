package com.example.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CottageReservationDTO {
    public UUID id;
    public String name;
    public String description;
    public Integer numberOfBeds;
    public int numberOfRooms;
    public String address;
    public Double price;
    public String img;

}
