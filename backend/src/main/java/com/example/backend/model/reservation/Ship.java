package com.example.backend.model.reservation;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "ships")
public class Ship extends ReservationEntity{

    private int capacity;

    private String typeOfBoat;

    private Double length;

    private String engineNumber;

    private Double enginePower;

    private Double maxSpeed;

    private Double percentageForCanceled;

    @ElementCollection
    private List<String> additionalService = new ArrayList<String>();

    private String cancellationCondition;

}
