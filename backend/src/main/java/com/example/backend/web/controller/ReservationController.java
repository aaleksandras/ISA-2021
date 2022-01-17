package com.example.backend.web.controller;
import com.example.backend.email.EmailSender;
import com.example.backend.enums.StatusOfReservation;
import com.example.backend.model.reservation.Reservation;
import com.example.backend.model.user.User;
import com.example.backend.repository.ReservationRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.IReservationService;
import com.example.backend.web.dto.ActionDTO;
import com.example.backend.web.dto.ReservationActionDTO;
import com.example.backend.web.dto.ReservationDTO;
import com.example.backend.web.dto.ReservationDTO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender sender;

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CLIENT','ROLE_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping
    public ResponseEntity<?> createTerm(@RequestBody ReservationDTO dto) {
        UUID id = reservationService.create(dto);
        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CLIENT','ROLE_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/action")
    public ResponseEntity<?> action(@RequestBody ActionDTO dto) {
        Reservation r = reservationRepository.getById(dto.reservationId);
        User u = userRepository.getById(dto.getUserId());
        if(u == null || r == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        r.setUser(u);
        reservationRepository.save(r);
        try {
            sender.sendBookNotify(u.getEmail(), r.getId().toString());
        }catch (Exception e){
        }
       return ResponseEntity.ok().body(null);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CLIENT','ROLE_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping("/action/{id}")
    public ResponseEntity<?> getAllAction(@PathVariable UUID id) {
        List<Reservation> list = reservationRepository.getReservationByReservationIdAndStatusOfReservationAndUserIsNull(id, StatusOfReservation.action);
        List<ReservationActionDTO> dto = new ArrayList<>();
        for(Reservation r: list){
            dto.add(new ReservationActionDTO(
                    r.getId(),
                    r.getTerm().getStartDate(),
                    r.getTerm().getStartTime(),
                    r.getTerm().getEndDate(),
                    r.getTerm().getEndTime(),
                    r.getNumberOfPersons(),
                    r.getReservation().getName(),
                    r.getDiscount(),
                    r.getPrice()
                    ));
        }


        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CLIENT','ROLE_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_INSTRUCTOR')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getAllReservationByUserId(@PathVariable UUID id) {
        List<ReservationDTO2> dtos = reservationService.getAllReservationByUser(id);

        if(id == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CLIENT','ROLE_HOUSE_OWNER','ROLE_BOAT_OWNER','ROLE_INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Reservation r = reservationRepository.getById(id);
        r.setDeleted(true);
        reservationRepository.save(r);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
