package com.example.backend.repository;

import com.example.backend.model.penalty.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PenaltyRepository extends JpaRepository<Penalty, UUID> {
}
