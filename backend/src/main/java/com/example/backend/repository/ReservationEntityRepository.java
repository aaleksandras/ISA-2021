package com.example.backend.repository;

import com.example.backend.model.reservation.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, UUID> {
}
