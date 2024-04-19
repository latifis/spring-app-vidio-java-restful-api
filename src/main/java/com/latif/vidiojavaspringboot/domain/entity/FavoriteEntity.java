package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "mst_favorite")
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite")
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "favorite_vidio",
            joinColumns = @JoinColumn(name = "favorite_id"),
            inverseJoinColumns = @JoinColumn(name = "vidio_id")
    )
    private Set<VidioEntity> vidioEntities;

    @ManyToOne
    @JoinColumn(name = "user_added", nullable = false)
    private UserEntity userEntity;

    @Column(name = "dt_added")
    private LocalDate dtAdded;

    @Column(name = "dt_updated")
    private LocalDate dtUpdated;

}
