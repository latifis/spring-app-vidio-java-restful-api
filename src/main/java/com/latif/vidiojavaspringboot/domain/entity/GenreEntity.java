package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Integer id;

    @Column(name = "genre_name")
    private String name;

    @ManyToMany(mappedBy = "genreEntities")
    private Set<VidioEntity> vidioEntities;
//    @OneToMany(mappedBy = "idGenre", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var vidios: MutableList<VidioEntity> = mutableListOf()
}

