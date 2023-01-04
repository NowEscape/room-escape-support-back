package com.example.roomescapesupportback.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "theme")
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
    private boolean isClosed;

    @ManyToOne(targetEntity = CafeEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "cafe_id")
    private CafeEntity cafeEntity;
}
