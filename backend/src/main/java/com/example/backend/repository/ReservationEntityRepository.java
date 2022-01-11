package com.example.backend.repository;

import com.example.backend.enums.TypeOfEntity;
import com.example.backend.model.reservation.ReservationEntity;
import com.example.backend.web.dto.ReservationEntityParamsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, UUID> {

    @Query("select re from ReservationEntity re where re.type = :type")
    List<ReservationEntity> search(@Param("type") TypeOfEntity type);

    ReservationEntity findReservationEntitiesById(UUID id);

}
