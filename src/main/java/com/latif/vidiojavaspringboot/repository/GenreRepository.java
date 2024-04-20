package com.latif.vidiojavaspringboot.repository;

import com.latif.vidiojavaspringboot.domain.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

    GenreEntity findByName(String name);

}

