package com.example.backend.model.reservation;


import com.example.backend.model.DefaultModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pictures")
public class Picture extends DefaultModel {
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationEntity reservationEntity;
}
