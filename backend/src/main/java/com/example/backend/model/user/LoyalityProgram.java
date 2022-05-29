package com.example.backend.model.user;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class LoyalityProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProgram;

    @Column
    private int clientPointsForReservation;

    @Column
    private int pointsToRegular;
    @Column
    private int pointsToSilver;
    @Column
    private int pointsToGold;
    @Column
    private int percentRegular;
    @Column
    private int percentSilver;
    @Column
    private int percentGold;

    public LoyalityProgram() {
        super();
    }

    public UUID getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(UUID idProgram) {
        this.idProgram = idProgram;
    }

    public int getPointsToRegular() {
        return pointsToRegular;
    }

    public void setPointsToRegular(int pointsToRegular) {
        this.pointsToRegular = pointsToRegular;
    }

    public int getPointsToSilver() {
        return pointsToSilver;
    }

    public void setPointsToSilver(int pointsToSilver) {
        this.pointsToSilver = pointsToSilver;
    }

    public int getPointsToGold() {
        return pointsToGold;
    }

    public void setPointsToGold(int pointsToGold) {
        this.pointsToGold = pointsToGold;
    }

    public int getPercentRegular() {
        return percentRegular;
    }

    public void setPercentRegular(int percentRegular) {
        this.percentRegular = percentRegular;
    }

    public int getPercentSilver() {
        return percentSilver;
    }

    public void setPercentSilver(int percentSilver) {
        this.percentSilver = percentSilver;
    }

    public int getPercentGold() {
        return percentGold;
    }

    public void setPercentGold(int percentGold) {
        this.percentGold = percentGold;
    }

    public int getClientPointsForReservation() {
        return clientPointsForReservation;
    }

    public void setClientPointsForReservation(int clientPointsForReservation) {
        this.clientPointsForReservation = clientPointsForReservation;
    }

}