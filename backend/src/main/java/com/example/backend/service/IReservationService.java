package com.example.backend.service;

import com.example.backend.model.reservation.Reservation;
import com.example.backend.web.dto.ComplaintDTO;
import com.example.backend.web.dto.ReservationDTO;
import com.example.backend.web.dto.ReservationDTO2;
import com.example.backend.web.dto.RevisionDTO;

import java.util.List;
import java.util.UUID;

public interface IReservationService {
    UUID create(ReservationDTO dto);

    List<ReservationDTO2> getAllReservationByUser(UUID id);

    Reservation createRevision(RevisionDTO dto);

    Reservation createComplaint(ComplaintDTO dto);
}
