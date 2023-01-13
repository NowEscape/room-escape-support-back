package com.example.roomescapesupportback.model.entity;

import com.example.roomescapesupportback.model.DTO.Genre;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false, updatable = false, insertable = false)
    private Integer genreId;

    @Column(name = "genre_name", nullable = false)
    private String genreName;

    @OneToMany(mappedBy = "genreEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ThemeEntity> themeEntityList = new ArrayList<>();

    public void addThemeEntity(ThemeEntity themeEntity) {
        this.themeEntityList.add(themeEntity);
        themeEntity.setGenreEntity(this);
    }

    public Genre toDto() {
        return Genre.builder()
                .genreId(genreId)
                .genreName(genreName)
                .build();
    }
}
