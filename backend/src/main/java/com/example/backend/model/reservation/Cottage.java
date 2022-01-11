package com.example.backend.model.reservation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "cottages")
public class Cottage extends ReservationEntity {

    private int numberOfRooms;
    private int numberOfBeds;
    
}
