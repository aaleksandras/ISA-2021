package com.example.backend.web.dto;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
public class ReservationDTO {
    private Date startDate;
    private LocalTime startTime;
    private Integer numberOfDay;
    private UUID reservationEntityId;
    private UUID userId;
    private Integer type;
}
