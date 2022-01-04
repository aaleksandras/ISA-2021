package com.example.backend.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "clients")
public class Client extends User implements Serializable {
}
