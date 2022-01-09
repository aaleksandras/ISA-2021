package com.example.backend.web.controller;

import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.security.reservation.ReservationEntityService;
import com.example.backend.web.dto.ReservationEntityParamsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/all")
    public ResponseEntity<List<ReservationEntity>> findAll(ReservationEntityParamsDTO params) {
        return ResponseEntity.ok().body(service.search(params));
    }


}
