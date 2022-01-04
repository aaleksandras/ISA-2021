package com.example.backend.model.place;

import com.example.backend.model.DefaultModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address extends DefaultModel {

    private String street;
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

}
