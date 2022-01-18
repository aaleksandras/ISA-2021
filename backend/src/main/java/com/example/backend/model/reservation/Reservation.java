package com.example.backend.model.reservation;

import com.example.backend.enums.StatusOfComplaint;
import com.example.backend.enums.StatusOfReservation;
import com.example.backend.enums.StatusOfRevision;
import com.example.backend.model.DefaultModel;
import com.example.backend.model.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Where(clause = "deleted = 'false'")
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

    @Column(length = 4096)
    private String complaint;

    @Column(length = 4096)
    private String answer;

    private StatusOfComplaint statusOfComplaint;

    @Column(length = 4096)
    private String revision;

    Integer mark;

    StatusOfRevision status;

}
