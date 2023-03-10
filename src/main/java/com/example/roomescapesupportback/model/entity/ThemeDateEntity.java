package com.example.roomescapesupportback.model.entity;

import com.example.roomescapesupportback.model.DTO.ThemeDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "theme_date", indexes = {
    @Index(name = "theme_date_index1", columnList = "theme_id"),
    @Index(name = "theme_date_index2", columnList = "theme_time,theme_id", unique = true)
})
@Getter
@Setter
public class ThemeDateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "theme_date_id")
  private int themeDateId;

  @ManyToOne(targetEntity = ThemeEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "theme_id")
  private ThemeEntity themeEntity;

  @Column(name = "theme_time")
  private LocalDateTime themeTime;

  @Column(name = "isOpen")
  private Boolean isOpen;

  @Column(name = "last_update_date", nullable = false, updatable = true, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime lastUpdateDate;

  public void setThemeEntity(ThemeEntity themeEntity) {
    this.themeEntity = themeEntity;
    themeEntity.getThemeDateEntityList().add(this);
  }

  public ThemeDate toDto() {
    return ThemeDate.builder()
        .themeTime(themeTime)
        .isOpen(isOpen)
        .build();
  }

}
