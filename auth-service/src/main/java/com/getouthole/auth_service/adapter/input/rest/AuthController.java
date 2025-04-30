package com.getouthole.auth_service.adapter.input.rest;

import com.getouthole.auth_service.application.usecase.AuthUseCases;
import com.getouthole.auth_service.domain.dto.LoginRequest;
import com.getouthole.auth_service.domain.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCases authUseCase;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authUseCase.register(request);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authUseCase.login(request);
        return ResponseEntity.ok(token);
    }
}