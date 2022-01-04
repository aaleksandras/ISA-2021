package com.example.backend.model.place;

import com.example.backend.model.DefaultModel;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "cities")
public class City extends DefaultModel {

    private String name;
    private Integer code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    Set<Address> addresses = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;
}
