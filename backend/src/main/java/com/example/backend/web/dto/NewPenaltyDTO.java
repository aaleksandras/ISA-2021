package com.example.backend.web.dto;

import com.example.backend.enums.StatusOfPenalty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPenaltyDTO {
    private String reason;
    private UUID id;
}
