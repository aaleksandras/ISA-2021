package com.example.backend.web.controller;

import com.example.backend.service.IAvailableDayService;
import com.example.backend.web.dto.AvailableDayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/availableDays", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailableDayController {
    @Autowired
    private IAvailableDayService availableDayService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody AvailableDayDTO dto) {
        availableDayService.create(dto);
        return ResponseEntity.ok().body(null);
    }


}
