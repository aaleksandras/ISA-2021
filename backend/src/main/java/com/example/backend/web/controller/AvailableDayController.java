package com.example.backend.web.controller;

import com.example.backend.model.reservation.*;
import com.example.backend.model.user.Client;
import com.example.backend.model.user.LoyalityProgram;
import com.example.backend.repository.*;
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

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AvailableDayDTO dto) {
        availableDayService.create(dto);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/searchShip")
    public ResponseEntity<List<ShipReservationDTO>> searchShip(@RequestBody SearchAvailableDayDTO dto) {
        List<ReservationEntity> list =  availableDayService.search(dto);
        List<ShipReservationDTO> dtos = new ArrayList<>();
        Client client = clientRepository.getById(dto.getUserID());
        String category = client.getLoyaltyCategory().toString();
        int percent = getPercent(category);

        for(ReservationEntity r: list){
            Ship s = shipRepository.findShipById(r.getId());
            String name = "";
            if(s.getPictures().size()>0){
                name = s.getPictures().iterator().next().getName();
            }
            ShipReservationDTO sd = new ShipReservationDTO(s.getId(),
                    s.getName(),
                    s.getDescription(),
                    s.getAddress(),
                    s.getPrice(),
                    s.getPrice() - s.getPrice()*0.01*percent,
                    s.getLength(),
                    s.getEngineNumber(),
                    s.getEnginePower(),
                    s.getMaxSpeed(),
                    name);
            dtos.add(sd);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/searchAdventure")
    public ResponseEntity<List<AdventureReservationDTO>> searchAdventure(@RequestBody SearchAvailableDayDTO dto) {
        List<ReservationEntity> list =  availableDayService.search(dto);
        Client client = clientRepository.getById(dto.getUserID());
        String category = client.getLoyaltyCategory().toString();
        int percent = getPercent(category);

        List<AdventureReservationDTO> dtos = new ArrayList<>();
        for(ReservationEntity r: list){
            Adventure a = adventureRepository.findAdventureById(r.getId());
            AdventureReservationDTO advedto = new AdventureReservationDTO();
            advedto.setAddress(a.getAddress());
            advedto.setDescription(a.getDescription());
            advedto.setId(a.getId());
            advedto.setMaxPersons(a.getMaxPersons());
            advedto.setName(a.getName());
            advedto.setDiscountedPrice(a.getPrice() - a.getPrice()*0.01*percent);
            advedto.setPrice(a.getPrice());
            if(a.getPictures().size() > 0){
                Picture p = a.getPictures().iterator().next();
                advedto.setImg(p.getName());
            }
            dtos.add(advedto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public int getPercent(String category){
        List<LoyalityProgram> programs = loyaltyProgramRepository.findAll();
        if (programs.isEmpty()) {
            System.out.println("Ne postoji loyalty program");
            return 0;
        } else {
            System.out.println("postoji lojaliti");
            LoyalityProgram program = programs.get(0);
            int percent = 0;
            if(category.equals("REGULAR")){
                percent = program.getPercentRegular();
            }else if(category.equals("SILVER")){
                percent = program.getPercentSilver();
            }else if(category.equals("GOLD")){
                percent = program.getPercentGold();
            }else{
                percent = 0;
            }

            return percent;
        }
    }

    @PostMapping("/searchCottage")
    public ResponseEntity<List<CottageReservationDTO>> searchCottage(@RequestBody SearchAvailableDayDTO dto) {
        List<ReservationEntity> list =  availableDayService.search(dto);
        List<CottageReservationDTO> dtos = new ArrayList<>();
        Client client = clientRepository.getById(dto.getUserID());
        String category = client.getLoyaltyCategory().toString();
        int percent = getPercent(category);

        for(ReservationEntity r: list){
            Cottage c = cottageRepository.findCottageById(r.getId());
            String name = "";
            if(c.getPictures().size()>0){
                name = c.getPictures().iterator().next().getName();
            }
            CottageReservationDTO cd = new CottageReservationDTO(
                    c.getId(),
                    c.getName(),
                    c.getDescription(),
                    c.getNumberOfBeds(),
                    c.getNumberOfRooms(),
                    c.getAddress(),
                    c.getPrice(),
                    c.getPrice() - c.getPrice()*0.01*percent,
                    name
            );
            dtos.add(cd);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}
