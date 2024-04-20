package com.latif.vidiojavaspringboot.repository;

import com.latif.vidiojavaspringboot.domain.entity.TypeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeUserRepository extends JpaRepository<TypeUserEntity, String> {

    TypeUserEntity findByTypeUser(String typeUser);

}
