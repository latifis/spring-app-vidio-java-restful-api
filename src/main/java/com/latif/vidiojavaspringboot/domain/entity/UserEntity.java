package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private TypeUserEntity typeUserEntity;

    @OneToMany(mappedBy = "userEntity")
    private Set<FavoriteEntity> favoriteEntities;
//    @OneToMany(mappedBy = "idUser", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var favorites: MutableList<FavoriteEntity> = mutableListOf()

}
