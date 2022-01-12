package com.example.backend.model.reservation;

import com.example.backend.enums.StatusOfReservation;
import com.example.backend.model.DefaultModel;
import com.example.backend.model.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservations")
public class Reservation extends DefaultModel {

    @OneToOne
    @JoinColumn(name = "term_id", referencedColumnName = "id")
    private Term term;

    private Double price;

    private StatusOfReservation statusOfReservation;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name="reservation_entyty_id")
    private ReservationEntity reservation;

    private Integer numberOfPersons;

    private Integer discount;

    @ElementCollection
    private List<String> additionalService = new ArrayList<String>();
}
