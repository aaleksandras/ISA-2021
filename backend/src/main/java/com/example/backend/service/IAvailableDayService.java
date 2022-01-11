package com.example.backend.service;

import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.web.dto.AvailableDayDTO;
import com.example.backend.web.dto.SearchAvailableDayDTO;

import java.util.List;

public interface IAvailableDayService {
    void create(AvailableDayDTO dto);

    List<ReservationEntity> search(SearchAvailableDayDTO dto);
}
