package com.example.backend.model.reservation;

import com.example.backend.model.DefaultModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "rules")
public class Rule extends DefaultModel {

    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Set<ReservationEntityRule> reservationEntityRuleSet;
}
