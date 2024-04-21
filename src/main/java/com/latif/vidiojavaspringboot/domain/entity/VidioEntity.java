package com.latif.vidiojavaspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "mst_vidio")
public class VidioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vidio")
    private Integer id;

    @Column(name = "name_vidio")
    private String name;

    @Column(name = "creator_vidio")
    private String creator;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private TypeUserEntity type;

    @ManyToMany
    @JoinTable(
            name = "vidio_genre",
            joinColumns = @JoinColumn(name = "vidio_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genreEntities;

    @Column(name = "dt_added")
    private LocalDate added;

    @Column(name = "dt_updated")
    private LocalDate updated;

    @ManyToMany(mappedBy = "vidioEntities")
    private Set<FavoriteEntity> favorite;

}
