package com.example.backend.security.reservation;

import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.repository.ReservationEntityRepository;
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
}
