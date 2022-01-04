package com.example.backend.model.place;

import com.example.backend.model.DefaultModel;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "countries")
@Where(clause = "deleted = 'false'")
public class Country extends DefaultModel {

    private String name;
}
