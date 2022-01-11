package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.reservation.AvailableDay;

import java.util.Date;
import java.util.UUID;

public interface AvailableDayRepository extends JpaRepository<AvailableDay, UUID> {

    AvailableDay findAvailableDayByDayAndReservationId(Date day, UUID id);
}
