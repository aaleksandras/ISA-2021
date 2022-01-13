package com.example.backend.web.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
public class ReservationDTO2 {
    private UUID id;
    private Date startDate;
    private LocalTime startTime;
    private Date endDate;
    private LocalTime endTime;
    private Integer numberOfPersons;
    private String name;
    private Double price;
}
