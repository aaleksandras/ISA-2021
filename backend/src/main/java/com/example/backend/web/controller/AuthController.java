package com.example.backend.web.controller;

import com.example.backend.model.auth.AuthRequest;
import com.example.backend.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    IAuthenticationService authService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest dto) {
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }
}
