package com.example.backend.repository;

import com.example.backend.model.user.HouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HouseOwnerRepository extends JpaRepository<HouseOwner, UUID> {
}
