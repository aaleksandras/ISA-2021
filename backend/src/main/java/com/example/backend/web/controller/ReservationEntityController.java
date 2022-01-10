package com.example.backend.web.controller;

import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.model.user.User;
import com.example.backend.repository.ReservationEntityRepository;
import com.example.backend.security.reservation.ReservationEntityService;
import com.example.backend.web.dto.ReservationEntityParamsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/reservation-entity", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationEntityController {

    private final ReservationEntityService service;

    @Autowired
    ReservationEntityRepository reservationEntityRepository;

    @GetMapping
    public ResponseEntity<List<ReservationEntity>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }


    @GetMapping("/all")
    public ResponseEntity<List<ReservationEntity>> findAll(ReservationEntityParamsDTO params) {
        return ResponseEntity.ok().body(service.search(params));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @GetMapping("/deleteEntity/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        ReservationEntity entity = reservationEntityRepository.findById(UUID.fromString(id)).get();
        entity.setDeleted(true);
        reservationEntityRepository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }


}
