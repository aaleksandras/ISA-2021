package com.example.backend.repository;

import com.example.backend.model.reservation.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TermRepository extends JpaRepository<Term, UUID> {
}
