package com.example.backend.model.reservation;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "adventures")
public class Adventure extends ReservationEntity {
    private int maxPersons;

    @ElementCollection
    private List<String> additionalService = new ArrayList<String>();

    private String cancellationCondition;
}
