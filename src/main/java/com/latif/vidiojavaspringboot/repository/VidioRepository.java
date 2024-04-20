package com.latif.vidiojavaspringboot.repository;

import com.latif.vidiojavaspringboot.domain.entity.TypeUserEntity;
import com.latif.vidiojavaspringboot.domain.entity.VidioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VidioRepository extends JpaRepository<VidioEntity, Integer> {

    List<VidioEntity> findByType(TypeUserEntity typeUserEntity);

}
