package com.example.backend.security.reservation;

import com.example.backend.enums.TypeOfEntity;
import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.repository.ReservationEntityRepository;
import com.example.backend.web.dto.ReservationEntityParamsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationEntityService {

    private final ReservationEntityRepository repository;

    public List<ReservationEntity> findAll() {
        return repository.findAll();
    }

    public List<ReservationEntity> search(ReservationEntityParamsDTO params) {
        TypeOfEntity type = null;
        if (params.getType() == 0) {
            type = TypeOfEntity.SHIP;
        } else if (params.getType() == 1) {
            type = TypeOfEntity.COTTAGE;
        } else if (params.getType() == 2) {
            type = TypeOfEntity.ADVENTURE;
        }
        return repository.search(type);
    }
}
