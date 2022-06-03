package com.example.backend.service.impl;

import com.example.backend.email.EmailSender;
import com.example.backend.enums.StatusOfComplaint;
import com.example.backend.enums.StatusOfReservation;
import com.example.backend.enums.StatusOfRevision;
import com.example.backend.model.reservation.*;
import com.example.backend.model.user.User;
import com.example.backend.repository.*;
import com.example.backend.service.IReservationService;
import com.example.backend.web.dto.ComplaintDTO;
import com.example.backend.web.dto.ReservationDTO;
import com.example.backend.web.dto.ReservationDTO2;
import com.example.backend.web.dto.RevisionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private AvailableDayRepository availableDayRepository;
    @Autowired
    private ReservationEntityRepository reservationEntityRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender sender;
    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UUID create(ReservationDTO dto) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = startDate.plusDays(dto.getNumberOfDay() - 1);
        List<AvailableDay> availableDayList = new ArrayList<>();
        ReservationEntity reservation = reservationEntityRepository.findReservationEntitiesById(dto.getReservationEntityId());
        if (reservation == null) return null;
        List<Reservation> reservationList = reservationRepository.timeOverlapping(dto.getStartDate(), dto.getUserId());
        if (!reservationList.isEmpty()) {
            return null;
        }
        User user = userRepository.findUserById(dto.getUserId());
        if (user == null) return null;
        for (int i = 0; i <= dto.getNumberOfDay(); i++) {
            if (i != 0) {
                startDate = startDate.plusDays(1);
            }
            if (startDate.isBefore(endDate) || startDate.equals(endDate)) {
                Date start = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
                AvailableDay availableDay = availableDayRepository.findAvailableDayByDayAndReservationId(start, reservation.getId());
                if (availableDay != null && !availableDay.getIs_free()) {
                    return null;
                }
                availableDay.setIs_free(false);
                availableDayList.add(availableDay);
            }
        }

        //Sracunaj popust
        Double newPrice = userService.getNewPrice(reservation.getPrice(), dto.getUserId());

        //dodati poene i promeniti kategoriju klijentu
        userService.addPointsAndChangeCategory(dto.getUserId());

        Reservation r = new Reservation();
        Term term = new Term();
        term.setStartDate(dto.getStartDate());
        term.setEndDate(Date.from(endDate.atStartOfDay(defaultZoneId).toInstant()));
        term.setStartTime(LocalTime.now());
        term.setEndTime(LocalTime.now());
        term = termRepository.save(term);
        r.setTerm(term);
        r.setStatusOfReservation(StatusOfReservation.SCHEDULED);
        r.setReservation(reservation);
        double p = newPrice * dto.getNumberOfDay();
        r.setPrice(p);

        r.setUser(user);
        r.setNumberOfPersons(4);
        r = reservationRepository.save(r);
        if (r.getId() != null) {
            for (AvailableDay a : availableDayList) {
                a.setIs_free(false);
                availableDayRepository.save(a);
            }
        }

        try {

            sender.sendBookNotify(user.getEmail(), r.getId().toString());
        } catch (Exception e) {
        }
        return r.getId();
    }


    @Override
    public List<ReservationDTO2> getAllReservationByUser(UUID id) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startDate = LocalDate.now();
        startDate = startDate.plusDays(3);
        Date start = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
        List<Reservation> reservations = reservationRepository.getAllUserByReservation(start, id);
        List<ReservationDTO2> dtos = new ArrayList<>();
        for (Reservation r : reservations) {
            ReservationDTO2 rdt = new ReservationDTO2();
            rdt.setId(r.getId());
            rdt.setStartDate(r.getTerm().getStartDate());
            rdt.setEndDate(r.getTerm().getEndDate());
            rdt.setPrice(r.getPrice());
            rdt.setNumberOfPersons(r.getNumberOfPersons());
            rdt.setStartTime(r.getTerm().getStartTime());
            rdt.setEndTime(r.getTerm().getEndTime());
            rdt.setName(r.getReservation().getName());
            rdt.setMark(r.getMark());
            rdt.setRevision(r.getRevision());
            rdt.setAnswer(r.getAnswer());
            rdt.setComplaint(r.getComplaint());
            rdt.setStatus(r.getStatus());
            rdt.setStatusOfComplaint(r.getStatusOfComplaint());

            dtos.add(rdt);
        }

        return dtos;
    }

    @Override
    public List<ReservationDTO2> getEndedReservationByUser(UUID id) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate endDate = LocalDate.now();
        Date end = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
        List<Reservation> reservations = reservationRepository.getEndedUserByReservation(end, id);
        List<ReservationDTO2> dtos = new ArrayList<>();
        System.out.println(reservations.size() + "AAA");
        for (Reservation r : reservations) {
            ReservationDTO2 rdt = new ReservationDTO2();
            rdt.setId(r.getId());
            rdt.setStartDate(r.getTerm().getStartDate());
            rdt.setEndDate(r.getTerm().getEndDate());
            rdt.setPrice(r.getPrice());
            rdt.setNumberOfPersons(r.getNumberOfPersons());
            rdt.setStartTime(r.getTerm().getStartTime());
            rdt.setEndTime(r.getTerm().getEndTime());
            rdt.setName(r.getReservation().getName());
            rdt.setMark(r.getMark());
            rdt.setRevision(r.getRevision());
            rdt.setAnswer(r.getAnswer());
            rdt.setComplaint(r.getComplaint());
            rdt.setStatus(r.getStatus());
            rdt.setStatusOfComplaint(r.getStatusOfComplaint());

            dtos.add(rdt);
        }

        return dtos;
    }

    @Override
    public Reservation createRevision(RevisionDTO dto) {
        Reservation r = reservationRepository.getById(dto.getId());
        r.setRevision(dto.getRevision());
        r.setMark(dto.getMark());
        Revision revision =new Revision(dto, r);
        if (dto.getStatus() == null) {
            r.setStatus(StatusOfRevision.HOLD_ON);
            try {
                sender.sendComplaint("marko@gmail.com", "Nova revizija: " + r.getRevision());
            } catch (Exception e) {
            }
        } else if (dto.getStatus() == 1) {
            r.setStatus(StatusOfRevision.ACCEPTED);
            try {
                sender.sendComplaint(r.getUser().getEmail(),  "Prihvacena revizija: " + r.getRevision());
            } catch (Exception e) {
            }

        } else if (dto.getStatus() == 0) {
            try {
                sender.sendComplaint(r.getUser().getEmail(), "Odbijena revizija: " + r.getRevision());
            } catch (Exception e) {
            }

            r.setStatus(StatusOfRevision.DECLINED);
        }
        revisionRepository.save(revision);
        return reservationRepository.save(r);
    }

    @Override
    public Reservation createComplaint(ComplaintDTO dto) {
        Reservation r = reservationRepository.getById(dto.getId());
        r.setComplaint(dto.getComplaint());
        r.setAnswer(dto.getAnswer());
        if (dto.getStatus() == 0) {
            r.setStatusOfComplaint(StatusOfComplaint.SEND);
            try {
                sender.sendComplaint("marko@gmail.com", "Nova zalba: " + r.getComplaint());
            } catch (Exception e) {
            }

        } else if (dto.getStatus() == 1) {
            r.setStatusOfComplaint(StatusOfComplaint.ANSWERED);
            try {
                sender.sendComplaint(r.getUser().getEmail(), "Odgovor na zalbu: " + r.getAnswer());
            } catch (Exception e) {
            }

        }
        return reservationRepository.save(r);
    }


}
