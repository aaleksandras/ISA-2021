package com.example.backend.model.user;

import com.example.backend.model.DefaultModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role extends DefaultModel {

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
