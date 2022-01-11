package com.example.backend.repository;
import com.example.backend.model.reservation.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipRepository extends JpaRepository<Ship, UUID> {
    Ship findShipById(UUID id);
}
