package com.example.backend.web.dto;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class AvailableDayDTO {
    private Date startDate;
    private LocalTime startTime;
    private Date endDate;
    private LocalTime endTime;
    private UUID reservationEntityId;
}
