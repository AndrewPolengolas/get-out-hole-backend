package com.getouthole.auth_service.application.usecase;

import com.getouthole.auth_service.domain.dto.LoginRequest;
import com.getouthole.auth_service.domain.dto.RegisterRequest;

public interface AuthUseCases {

    String login(LoginRequest request);

    void register(RegisterRequest request);
}
