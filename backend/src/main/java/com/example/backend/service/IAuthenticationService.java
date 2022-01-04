package com.example.backend.service;

import com.example.backend.model.auth.AuthRequest;
import com.example.backend.model.auth.AuthResponse;

public interface IAuthenticationService {
    AuthResponse login(AuthRequest dto);
}
