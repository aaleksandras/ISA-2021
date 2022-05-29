package com.example.backend.web.dto;

import lombok.Data;

@Data
public class LoyaltyProgramDTO {
    int clientPointsForReservation;
    int pointsToRegular;
    int pointsToSilver;
    int pointsToGold;
    int percentRegular;
    int percentSilver;
    int percentGold;
}