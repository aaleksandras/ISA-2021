package com.example.backend.model.reservation;

import com.example.backend.enums.TypeOfEntity;
import com.example.backend.model.DefaultModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "reservation_entity")
public class ReservationEntity extends DefaultModel {

    private String title;

    @Column(length = 4096)
    private String description;

    private Double averageMark;

    private String address;

    private TypeOfEntity type;

}
