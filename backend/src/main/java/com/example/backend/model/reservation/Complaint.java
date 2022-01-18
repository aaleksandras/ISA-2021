package com.example.backend.model.reservation;

import com.example.backend.model.DefaultModel;
import com.example.backend.model.user.Client;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Complaint extends DefaultModel {

    @Column(length = 4096)
    private String content;

    @Column(length = 4096)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

}
