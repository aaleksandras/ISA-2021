package com.example.backend.service.impl;

import com.example.backend.model.reservation.AvailableDay;
import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.repository.AvailableDayRepository;
import com.example.backend.repository.ReservationEntityRepository;
import com.example.backend.service.IAvailableDayService;
import com.example.backend.web.dto.AvailableDayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AvailableDayServiceImpl implements IAvailableDayService {

    @Autowired
    private AvailableDayRepository availableDayRepository;
    @Autowired
    private ReservationEntityRepository reservationEntityRepository;

    @Override
    public void create(AvailableDayDTO dto) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = dto.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ReservationEntity reservation = reservationEntityRepository.findReservationEntitiesById(dto.getReservationEntityId());
        if(reservation == null) return;
        int i = 0;
        while (true){
            if(i != 0) startDate = startDate.plusDays(1);
            if(startDate.isBefore(endDate)){
                Date start = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
                AvailableDay is_exists = availableDayRepository.findAvailableDayByDay(start);
                if(is_exists != null){
                    throw new ExceptionInInitializerError();
                }
                AvailableDay availableDay = new AvailableDay();
                availableDay.setDay(start);
                availableDay.setTime(dto.getStartTime());
                availableDay.setReservation(reservation);
                availableDayRepository.save(availableDay);
                i++;
            }else {
                Date end = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
                System.out.println(end);
                AvailableDay is_exists = availableDayRepository.findAvailableDayByDay(end);
                if(is_exists != null){
                    throw new ExceptionInInitializerError();
                }
                AvailableDay availableDay = new AvailableDay();
                availableDay.setDay(dto.getEndDate());
                availableDay.setTime(dto.getEndTime());
                availableDay.setReservation(reservation);
                availableDayRepository.save(availableDay);
                break;
            }
        }
    }
}
