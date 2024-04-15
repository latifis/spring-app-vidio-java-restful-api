package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_vidio")
public class VidioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vidio")
    private Long idVidio;

    @Column(name = "name_vidio")
    private String nameVidio;

    @Column(name = "creator_vidio")
    private String creatorVidio;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type_id")
//    var typeId: TypeUserEntity? = null,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_genre")
//    var idGenre: GenreEntity? = null,

    @Column(name = "dt_added")
    private Date dtAdded;

    @Column(name = "dt_updated")
    private Date dtUpdatedl;

//    @OneToMany(mappedBy = "idVidio", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var favorites: MutableList<FavoriteEntity> = mutableListOf()
}
