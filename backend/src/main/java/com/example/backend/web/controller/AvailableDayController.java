package com.example.backend.web.controller;

import com.example.backend.model.reservation.Adventure;
import com.example.backend.model.reservation.Cottage;
import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.model.reservation.Ship;
import com.example.backend.repository.AdventureRepository;
import com.example.backend.repository.CottageRepository;
import com.example.backend.repository.ShipRepository;
import com.example.backend.service.IAvailableDayService;
import com.example.backend.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/availableDays", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailableDayController {
    @Autowired
    private IAvailableDayService availableDayService;

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AvailableDayDTO dto) {
        availableDayService.create(dto);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/searchShip")
    public ResponseEntity<List<ShipReservationDTO>> searchShip(@RequestBody SearchAvailableDayDTO dto) {
         List<ReservationEntity> list =  availableDayService.search(dto);
        List<ShipReservationDTO> dtos = new ArrayList<>();
        for(ReservationEntity r: list){
            Ship s = shipRepository.findShipById(r.getId());
            ShipReservationDTO sd = new ShipReservationDTO(s.getId(),
                    s.getName(),
                    s.getDescription(),
                    s.getAddress(),
                    s.getPrice(),
                    s.getLength(),
                    s.getEngineNumber(),
                    s.getEnginePower(),
                    s.getMaxSpeed());
            dtos.add(sd);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/searchAdventure")
    public ResponseEntity<List<AdventureReservationDTO>> searchAdventure(@RequestBody SearchAvailableDayDTO dto) {
        List<ReservationEntity> list =  availableDayService.search(dto);
        List<AdventureReservationDTO> dtos = new ArrayList<>();
        for(ReservationEntity r: list){
            Adventure a = adventureRepository.findAdventureById(r.getId());
            AdventureReservationDTO advedto = new AdventureReservationDTO();
            advedto.setAddress(a.getAddress());
            advedto.setDescription(a.getDescription());
            advedto.setId(a.getId());
            advedto.setMaxPersons(a.getMaxPersons());
            advedto.setName(a.getName());
            dtos.add(advedto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/searchCottage")
    public ResponseEntity<List<CottageReservationDTO>> searchCottage(@RequestBody SearchAvailableDayDTO dto) {
        List<ReservationEntity> list =  availableDayService.search(dto);
        List<CottageReservationDTO> dtos = new ArrayList<>();
        for(ReservationEntity r: list){
            Cottage c = cottageRepository.findCottageById(r.getId());
            CottageReservationDTO cd = new CottageReservationDTO(
                    c.getId(),
                    c.getName(),
                    c.getDescription(),
                    c.getNumberOfBeds(),
                    c.getNumberOfRooms(),
                    c.getAddress(),
                    c.getPrice()
            );
            dtos.add(cd);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}
