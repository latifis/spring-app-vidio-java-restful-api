package com.latif.vidiojavaspringboot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mst_genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Integer id;

    @Column(name = "genre_name")
    private String genreName;

}

