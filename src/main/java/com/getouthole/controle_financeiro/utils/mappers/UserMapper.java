package com.getouthole.controle_financeiro.utils.mappers;

import com.getouthole.controle_financeiro.adapter.output.entities.JpaUserEntity;
import com.getouthole.controle_financeiro.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "jpa.id", target = "id"),
            @Mapping(source = "jpa.email", target = "email"),
            @Mapping(source = "jpa.password", target = "password"),
            @Mapping(source = "jpa.name", target = "name"),
            @Mapping(source = "jpa.roles", target = "roles"),
    })
    User jpaToDomain(JpaUserEntity jpa);

    @Mappings({
            @Mapping(source = "user.id", target = "id"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.password", target = "password"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.roles", target = "roles"),
    })
    JpaUserEntity domainToJpa(User user);
}
