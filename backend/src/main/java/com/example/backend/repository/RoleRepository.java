package com.example.backend.repository;

import com.example.backend.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findRoleByName(String name);
}
