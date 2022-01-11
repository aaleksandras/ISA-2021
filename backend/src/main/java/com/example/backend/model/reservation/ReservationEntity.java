package com.example.backend.model.reservation;

import com.example.backend.enums.TypeOfEntity;
import com.example.backend.model.DefaultModel;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Data
@Entity
@Where(clause = "deleted = 'false'")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "reservation_entity")
public class ReservationEntity extends DefaultModel {

    private String name;

    @Column(length = 4096)
    private String description;

    private Double averageMark;

    private String address;

    private TypeOfEntity type;

    @OneToMany(mappedBy="reservation")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy="reservation")
    private Set<AvailableDay> availableDays;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservationEntity")
    private Set<Picture> pictures = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservationEntity")
    private Set<ReservationEntityRule> reservationEntityRules = new HashSet<>();

    private Double price;
}
