package com.example.backend.repository;

import com.example.backend.model.reservation.Reservation;
import com.example.backend.model.reservation.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface RevisionRepository  extends JpaRepository<Revision, UUID> {

    List<Revision> findAllByUserId(UUID id);


}
