package com.example.backend.web.dto;
import com.example.backend.enums.StatusOfRevision;
import com.example.backend.model.reservation.Complaint;
import lombok.Data;

import javax.persistence.Column;
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
    private String complaint;
    private String answer;
    private String revision;
    Integer mark;
    StatusOfRevision status;
}
