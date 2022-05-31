package com.example.backend.web.dto;

import lombok.Data;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
public class SearchAvailableDayDTO {
    private Date startDate;
    private LocalTime startTime;
    private Integer numberOfDay;
    private Integer type;

    private UUID userID;
}
