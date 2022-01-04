package com.example.backend.service;

import com.example.backend.model.place.Country;
import com.example.backend.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryService {

    final private CountryRepository countryRepository;


    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }
}
