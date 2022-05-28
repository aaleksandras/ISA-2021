package com.example.backend.model.penalty;

import com.example.backend.enums.StatusOfPenalty;
import com.example.backend.model.DefaultModel;
import com.example.backend.web.dto.NewPenaltyDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "penalty")
public class Penalty extends DefaultModel {
    private String reason;
    private Date endDate;
    private StatusOfPenalty statusOfPenalty;

    private String userComment;

    private String username;

    public Penalty(NewPenaltyDTO dto, Date nextMonth) {
        this.reason = dto.getReason();
        this.endDate = nextMonth;
        this.statusOfPenalty = StatusOfPenalty.NEW;
    }

    public Penalty() {
    }
}
