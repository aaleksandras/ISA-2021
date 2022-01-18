package com.example.backend.model.reservation;

import com.example.backend.enums.StatusOfRevision;
import com.example.backend.model.DefaultModel;
import com.example.backend.model.user.Client;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Revision extends DefaultModel {

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    @Column(length = 4096)
    String content;

    Integer mark;

    StatusOfRevision status;
}
