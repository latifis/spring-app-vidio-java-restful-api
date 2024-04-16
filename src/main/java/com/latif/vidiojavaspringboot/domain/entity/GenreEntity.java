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
@Table(name = "mst_genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Long idGenre;

    @Column(name = "genre_name")
    private String genreName;

//    @OneToMany(mappedBy = "idGenre", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var vidios: MutableList<VidioEntity> = mutableListOf()

    @OneToMany(mappedBy = "genreEntity")
    private List<VidioEntity> vidioEntities;
}

