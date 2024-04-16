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
@Table(name = "mst_favorite")
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite")
    private Long idFavorite;

    @ManyToOne
    @JoinColumn(name= "id_vidio", referencedColumnName = "id_vidio")
    private VidioEntity vidio;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idVidio")
//    var idVidio: VidioEntity? = null,

    @ManyToOne
    @JoinColumn(name= "id_user", referencedColumnName = "id_user")
    private UserEntity user;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idUser")
//    var idUser: UserEntity? = null,

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_added")
    private Date dtAdded;

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_updated")
    private Date dtUpdated;
}
