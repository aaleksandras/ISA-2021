package com.example.backend.repository;

import com.example.backend.enums.StatusOfPenalty;
import com.example.backend.model.penalty.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PenaltyRepository extends JpaRepository<Penalty, UUID> {
    List<Penalty> getPenaltyByUsernameAndStatusOfPenalty(String username, StatusOfPenalty status);
}
