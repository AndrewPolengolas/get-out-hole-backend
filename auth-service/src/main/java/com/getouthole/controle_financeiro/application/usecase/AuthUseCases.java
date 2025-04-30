package com.getouthole.controle_financeiro.application.usecase;

import com.getouthole.controle_financeiro.domain.dto.LoginRequest;
import com.getouthole.controle_financeiro.domain.dto.RegisterRequest;

public interface AuthUseCases {

    String login(LoginRequest request);

    void register(RegisterRequest request);
}
