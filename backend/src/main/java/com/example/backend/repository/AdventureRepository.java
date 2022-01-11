package com.example.backend.repository;
import com.example.backend.model.reservation.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AdventureRepository extends JpaRepository<Adventure, UUID> {

    Adventure findAdventureById(UUID id);
}
