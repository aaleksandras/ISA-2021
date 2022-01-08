package com.example.backend.web.controller;

import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.security.reservation.ReservationEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/reservation-entity", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationEntityController {

    private final ReservationEntityService service;

    @GetMapping
    public ResponseEntity<List<ReservationEntity>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

}
