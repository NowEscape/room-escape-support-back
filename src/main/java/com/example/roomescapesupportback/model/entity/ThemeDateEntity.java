package com.example.roomescapesupportback.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "theme_date")
@Getter
@Setter
public class ThemeDateEntity {
    @Id
    @Column(name = "theme_date_id")
    private int themeDateId;

    @ManyToOne(targetEntity = ThemeEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private ThemeEntity themeEntity;

    private ZonedDateTime themeDate;

    public void setThemeEntity(ThemeEntity themeEntity) {
        this.themeEntity = themeEntity;
        themeEntity.getThemeDateEntityList().add(this);
    }
}
