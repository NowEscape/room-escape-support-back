package com.example.roomescapesupportback.model.entity;

import com.example.roomescapesupportback.model.DTO.ThemeDate;
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

    @Column(name = "theme_time")
    private ZonedDateTime themeTime;

    @Column(name = "last_update_date", nullable = false, updatable = true, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime lastUpdateDate;

    public void setThemeEntity(ThemeEntity themeEntity) {
        this.themeEntity = themeEntity;
        themeEntity.getThemeDateEntityList().add(this);
    }

    public ThemeDate toDto() {
        return ThemeDate.builder()
                .themeDateId(themeDateId)
                .themeTime(themeTime)
                .build();
    }

}
