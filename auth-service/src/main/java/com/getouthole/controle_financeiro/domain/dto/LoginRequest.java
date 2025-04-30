package com.getouthole.controle_financeiro.domain.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}