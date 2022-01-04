package com.example.backend.web.controller;

import com.example.backend.model.place.Country;
import com.example.backend.service.CountryService;
import com.example.backend.web.dto.place.CountryDTO;
import com.example.backend.web.mapper.place.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {


    final private CountryService countryService;
    final private CountryMapper countryMapper;

    @GetMapping
    ResponseEntity<List<CountryDTO>> findAll() {
        return ResponseEntity.ok().body(countryMapper.getListDtoFromListModel(countryService.findAll()));
    }




}
