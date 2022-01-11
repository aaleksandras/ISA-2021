package com.example.backend.repository;

import com.example.backend.model.user.DeleteAccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeleteAccountRequestRepository extends JpaRepository<DeleteAccountRequest, UUID> {
}
