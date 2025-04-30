package com.getouthole.controle_financeiro.domain.model;

import java.util.Optional;
import java.util.UUID;

public interface UserEntity {

    Optional<User> findByEmail(String email);

    void save(User user);

    Optional<User> findById(UUID id);
}
