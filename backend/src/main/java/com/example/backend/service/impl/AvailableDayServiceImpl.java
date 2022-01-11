package com.example.backend.service.impl;

import com.example.backend.enums.TypeOfEntity;
import com.example.backend.model.reservation.AvailableDay;
import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.model.reservation.Ship;
import com.example.backend.repository.AvailableDayRepository;
import com.example.backend.repository.ReservationEntityRepository;
import com.example.backend.service.IAvailableDayService;
import com.example.backend.web.dto.AvailableDayDTO;
import com.example.backend.web.dto.SearchAvailableDayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
                AvailableDay is_exists = availableDayRepository.findAvailableDayByDayAndReservationId(start, reservation.getId());
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
                AvailableDay is_exists = availableDayRepository.findAvailableDayByDayAndReservationId(end, reservation.getId());
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

    @Override
    public List<ReservationEntity>  search(SearchAvailableDayDTO dto) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        TypeOfEntity t = TypeOfEntity.ADVENTURE;
        switch (dto.getType()){
            case 0: t = TypeOfEntity.SHIP;
            break;
            case 1: t = TypeOfEntity.COTTAGE;
            break;
        }
        List<ReservationEntity> reservationEntities = reservationEntityRepository.search(t);
        List<LocalDate> days = new ArrayList<>();
        for (int i = 0; i <= dto.getNumberOfDay(); i++){
            if(i != 0){
                startDate = startDate.plusDays(1);
            }
            days.add(startDate);
        }
        List<ReservationEntity> availableReservationEntity = new ArrayList<>();
        int i;
        for (ReservationEntity e: reservationEntities) {
            i=0;
            for(LocalDate d: days){
                for(AvailableDay entityDay: e.getAvailableDays()){
                    LocalDate entityAvailableDay = entityDay.getDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if(d.equals(entityAvailableDay) && entityDay.getIs_free()){
                        i++;
                    }
                }
                if(i == days.size()){
                    availableReservationEntity.add(e);
                }
            }
        }

       return availableReservationEntity;
    }
}
