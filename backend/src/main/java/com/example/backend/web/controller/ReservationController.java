package com.example.backend.web.controller;

import com.example.backend.service.IReservationService;
import com.example.backend.web.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createTerm(@RequestBody ReservationDTO dto) {
        UUID id = reservationService.create(dto);
        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(id);
    }



}
