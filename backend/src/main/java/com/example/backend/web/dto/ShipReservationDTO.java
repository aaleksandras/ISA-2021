package com.example.backend.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ShipReservationDTO {
    public UUID id;
    public String name;
    public String description;
    public String address;
    public Double price;
    private Double length;
    private String engineNumber;
    private Double enginePower;
    private Double maxSpeed;
    public String img;

}
