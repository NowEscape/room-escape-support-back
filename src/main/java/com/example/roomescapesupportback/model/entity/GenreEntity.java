package com.example.roomescapesupportback.model.entity;

import com.example.roomescapesupportback.model.DTO.Genre;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genre", indexes = {
    @Index(name = "genre_index1", columnList = "genre_name", unique = true)
})
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
