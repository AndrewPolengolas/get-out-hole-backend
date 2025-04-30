package com.getouthole.controle_financeiro.domain.model;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private UUID id;
    private String email;
    private String password;
    private String name;
    private Set<String> roles;
}