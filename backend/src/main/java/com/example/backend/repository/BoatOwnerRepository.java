package com.example.backend.repository;

import com.example.backend.model.user.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner, UUID> {
}
