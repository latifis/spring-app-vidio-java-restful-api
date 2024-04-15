package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

//    @OneToMany(mappedBy = "user")
//    private List<Cart> carts;
//    @OneToMany(mappedBy = "idType", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var users: MutableList<UserEntity> = mutableListOf()
}

