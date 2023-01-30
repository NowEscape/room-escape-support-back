package com.example.roomescapesupportback.model.entity;

import com.example.roomescapesupportback.model.DTO.Theme;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "theme", indexes = {
    @Index(name = "theme_index1", columnList = "theme_name"),
    @Index(name = "theme_index2", columnList = "genre_id"),
    @Index(name = "theme_index3", columnList = "cafe_id")
})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "theme_id")
  private int themeId;

  @Column(name = "theme_name")
  private String themeName;

  @Column(name = "theme_description")
  private String themeDescription;

  @Column(name = "theme_image_url")
  private String themeImageUrl;

  @Column(name = "theme_open_date", columnDefinition = "TIMESTAMP comment '테마 오픈일'")
  private ZonedDateTime themeOpenDate;

  @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private ZonedDateTime createdDate;

  @Column(name = "updated_date", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private ZonedDateTime updatedDate;

  @Column(name = "is_closed", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
  private Boolean isClosed;

  @ManyToOne(targetEntity = CafeEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cafe_id")
  private CafeEntity cafeEntity;

  @ManyToOne(targetEntity = GenreEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "genre_id", nullable = true)
  private GenreEntity genreEntity;

  @JsonIgnore
  @OneToMany(mappedBy = "themeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ThemeDateEntity> themeDateEntityList = new ArrayList<>();

  public void addThemeDateEntity(ThemeDateEntity themeDateEntity) {
    themeDateEntity.setThemeEntity(this);
    themeDateEntityList.add(themeDateEntity);
  }

  public void setCafeEntity(CafeEntity cafeEntity) {
    this.cafeEntity = cafeEntity;
    cafeEntity.getThemeEntityList().add(this);
  }

  public void setGenreEntity(GenreEntity genreEntity) {
    this.genreEntity = genreEntity;
    genreEntity.getThemeEntityList().add(this);
  }

  public Theme toDto() {
    return Theme.builder()
        .themeName(themeName)
        .themeDescription(themeDescription)
        .themeImageUrl(themeImageUrl)
        .themeOpenDate(themeOpenDate)
        .createdDate(createdDate)
        .updatedDate(updatedDate)
        .genre(Optional.ofNullable(genreEntity).orElseGet(GenreEntity::new).toDto())
        .build();
  }
}
