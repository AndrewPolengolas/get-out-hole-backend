package com.getouthole.controle_financeiro.adapter.input.rest;

import com.getouthole.controle_financeiro.application.usecase.AuthUseCases;
import com.getouthole.controle_financeiro.domain.dto.LoginRequest;
import com.getouthole.controle_financeiro.domain.dto.RegisterRequest;
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