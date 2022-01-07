package com.example.backend.service.impl;

import com.example.backend.model.auth.AuthRequest;
import com.example.backend.model.auth.AuthResponse;
import com.example.backend.model.user.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.TokenUtils;
import com.example.backend.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthResponse login(AuthRequest dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User data = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        String jwt = tokenUtils.generateToken(data.getUsername(), authorities);
        int expiresIn = tokenUtils.getExpiredIn();

        AuthResponse responseDTO = new AuthResponse(data.getId(), data.getUsername(), jwt, data.getRoles().iterator().next().getName(), expiresIn, data.getIsActive());
        return responseDTO;
    }
}
