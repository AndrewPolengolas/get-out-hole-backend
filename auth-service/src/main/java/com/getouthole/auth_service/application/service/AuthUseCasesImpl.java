package com.getouthole.auth_service.application.service;

import com.getouthole.auth_service.adapter.output.repositories.UserRepositoryImpl;
import com.getouthole.auth_service.application.usecase.AuthUseCases;
import com.getouthole.auth_service.domain.dto.LoginRequest;
import com.getouthole.auth_service.domain.dto.RegisterRequest;
import com.getouthole.auth_service.domain.model.User;
import com.getouthole.auth_service.infrastructure.config.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthUseCasesImpl implements AuthUseCases {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepositoryImpl userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthUseCasesImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserRepositoryImpl userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        return jwtService.generateToken(user);
    }

    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singleton("ROLE_USER"));

        userRepository.save(user);
    }
}
