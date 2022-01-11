package com.example.backend.model.reservation;

import com.example.backend.model.DefaultModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "terms")
public class Term extends DefaultModel {

    private Date startDate;
    private LocalTime startTime;
    private Date endDate;
    private LocalTime endTime;
}
