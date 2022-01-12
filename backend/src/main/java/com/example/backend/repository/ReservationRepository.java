package com.example.backend.repository;

import com.example.backend.enums.StatusOfReservation;
import com.example.backend.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query("select r from Reservation r where r.term.startDate >= ?1 and r.term.endDate <= ?1")
    List<Reservation> timeOverlapping(Date startDate);


    List<Reservation> getReservationByReservationIdAndStatusOfReservationAndUserIsNull(UUID id, StatusOfReservation a);
}
