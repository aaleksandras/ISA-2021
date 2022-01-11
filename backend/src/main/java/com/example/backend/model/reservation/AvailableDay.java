package com.example.backend.model.reservation;

import com.example.backend.model.DefaultModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "available_days")
public class AvailableDay extends DefaultModel {

    private Date day;
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name="reservation_entyty_id")
    private ReservationEntity reservation;

    @Column(name = "is_free", nullable = false)
    private Boolean is_free = true;
}
