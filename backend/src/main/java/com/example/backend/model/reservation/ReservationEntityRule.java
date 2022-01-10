package com.example.backend.model.reservation;

import com.example.backend.model.DefaultModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation_entity_rule")
public class ReservationEntityRule extends DefaultModel {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationEntity reservationEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rule rule;

    private boolean allowed;
}
