package com.example.backend.model.reservation;

import com.example.backend.model.DefaultModel;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ReservationEntity extends DefaultModel {

    private String title;

    @Column(length = 4096)
    private String description;

    private Double averageMark;

    private String address;

}
