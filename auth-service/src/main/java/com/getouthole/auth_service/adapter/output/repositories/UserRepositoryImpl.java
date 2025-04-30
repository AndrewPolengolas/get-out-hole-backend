package com.getouthole.auth_service.adapter.output.repositories;

import com.getouthole.auth_service.adapter.output.entities.JpaUserEntity;
import com.getouthole.auth_service.domain.model.User;
import com.getouthole.auth_service.domain.model.UserEntity;
import com.getouthole.auth_service.utils.mappers.UserMapper;
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
