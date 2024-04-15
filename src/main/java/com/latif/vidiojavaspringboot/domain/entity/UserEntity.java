package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_type")
//    var idType: TypeUserEntity? = null,

//    @OneToMany(mappedBy = "idUser", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var favorites: MutableList<FavoriteEntity> = mutableListOf()

}
