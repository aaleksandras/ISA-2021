package com.example.backend.repository;

import com.example.backend.model.reservation.PercentageFromReservations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PercentageFromReservationsRepository extends JpaRepository<PercentageFromReservations, UUID> {
}
