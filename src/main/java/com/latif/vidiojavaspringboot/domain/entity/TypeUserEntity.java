package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_type_user")
public class TypeUserEntity {

    @Id
    @Column(name = "id_type")
    private String id;

    @Column(name = "type_user")
    private String typeUser;

    @OneToMany(mappedBy = "typeUserEntity")
    private Set<UserEntity> userEntities;

}

