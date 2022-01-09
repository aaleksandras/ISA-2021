package com.example.backend.model.reservation;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "ships")
public class Ship extends ReservationEntity{

    private int capacity;

    private Double percentageForCanceled;



}
