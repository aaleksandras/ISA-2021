package com.example.backend.web.dto;

import com.example.backend.enums.StatusOfComplaint;
import com.example.backend.enums.StatusOfRevision;
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
    private String complaint;
    private String answer;
    private String revision;
    private StatusOfComplaint statusOfComplaint;
    Integer mark;
    StatusOfRevision status;
}
