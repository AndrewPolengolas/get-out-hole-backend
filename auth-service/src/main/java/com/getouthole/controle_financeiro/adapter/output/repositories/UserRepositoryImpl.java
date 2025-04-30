package com.getouthole.controle_financeiro.adapter.output.repositories;

import com.getouthole.controle_financeiro.adapter.output.entities.JpaUserEntity;
import com.getouthole.controle_financeiro.domain.model.User;
import com.getouthole.controle_financeiro.domain.model.UserEntity;
import com.getouthole.controle_financeiro.utils.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepositoryImpl implements UserEntity {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<JpaUserEntity> userEntity = this.jpaUserRepository.findByEmail(email);
        return userEntity.map(userMapper::jpaToDomain);
    }

    @Override
    public void save(User user) {
        JpaUserEntity userEntity = userMapper.domainToJpa(user);
        jpaUserRepository.save(userEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        Optional<JpaUserEntity> userEntity = jpaUserRepository.findById(id);
        return userEntity.map(userMapper::jpaToDomain);
    }
}
