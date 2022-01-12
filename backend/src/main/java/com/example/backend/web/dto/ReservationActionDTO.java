package com.example.backend.web.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
public class ReservationActionDTO {
    private UUID id;
    private Date startDate;
    private LocalTime startTime;
    private Date endDate;
    private LocalTime endTime;
    private Integer numberOfPersons;
    private String name;
    private Integer discount;
    private Double price;

    public ReservationActionDTO(UUID id, Date startDate, LocalTime startTime, Date endDate, LocalTime endTime, Integer numberOfPersons, String name, Integer discount, Double price) {
        this.id = id;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.numberOfPersons = numberOfPersons;
        this.name = name;
        this.discount = discount;
        this.price = price;
    }
}
