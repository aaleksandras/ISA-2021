package com.example.backend.web.dto;

import com.example.backend.model.reservation.AvailableDay;
import com.example.backend.model.reservation.ReservationEntity;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationEntityResponse {

    ReservationEntity reservationEntity;
    List<AvailableDay> availableDays;
}
