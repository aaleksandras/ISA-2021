package com.example.backend.model.reservation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "adventures")
public class Adventure extends ReservationEntity {
    private int maxPersons;


}
