package com.example.backend.repository;

import com.example.backend.model.user.LoyalityProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoyaltyProgramRepository extends JpaRepository<LoyalityProgram, UUID> {
}