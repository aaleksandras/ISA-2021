package com.example.backend.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ActionDTO {
    public UUID userId;
    public UUID reservationId;
}
