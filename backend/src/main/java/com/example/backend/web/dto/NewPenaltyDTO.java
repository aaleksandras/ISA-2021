package com.example.backend.web.dto;

import com.example.backend.enums.StatusOfPenalty;
import com.example.backend.model.penalty.Penalty;
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
    private String username;
    private String userComment;

    private UUID id;

    public NewPenaltyDTO(Penalty p) {
        this.reason = p.getReason();
        this.username = "janko@gmail.com";
        this.userComment = p.getUserComment();
        this.id = p.getId();
    }
}
