package com.example.backend.service;

import com.example.backend.web.dto.ReservationDTO;

import java.util.UUID;

public interface IReservationService {
    UUID create(ReservationDTO dto);
}
