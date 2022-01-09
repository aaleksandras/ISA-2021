package com.example.backend.model.reservation;

import com.example.backend.enums.TypeOfEntity;
import com.example.backend.model.DefaultModel;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Where(clause = "deleted = 'false'")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "reservation_entity")
public class ReservationEntity extends DefaultModel {

    private String title;

    @Column(length = 4096)
    private String description;

    private Double averageMark;

    private String address;

    private TypeOfEntity type;

}
