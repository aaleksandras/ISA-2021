package com.example.backend.repository;

import com.example.backend.model.reservation.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CottageRepository extends JpaRepository<Cottage, UUID> {

    Cottage findCottageById(UUID id);
}
