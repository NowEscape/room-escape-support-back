package com.example.roomescapesupportback.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cafe")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id", nullable = false, unique = true,  updatable = false, insertable = false, columnDefinition = "INT(11)")
    private int cafeId;

    @Column(name = "cafe_name", nullable = false)
    private String cafeName;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name ="region_1", columnDefinition = "varchar(255) comment '시/도'")
    private String region1;

    @Column(name ="region2", columnDefinition = "varchar(255) comment '시/군/구'")
    private String region2;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cafe_description")
    private String cafeDescription;


    @Column(name = "cafe_image_url")
    private String cafeImageUrl;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime createdDate;

    @Column(name = "updated_date", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime updatedDate;

    @Column(name = "is_closed", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isClosed;

    @ManyToOne(targetEntity = CafeDomainEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_domain_id", nullable = false)
    private CafeDomainEntity cafeDomainEntity;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cafeEntity")
    private List<ThemeEntity> themeEntityList = new ArrayList<>();

    public void addThemeEntity(ThemeEntity themeEntity) {
        themeEntity.setCafeEntity(this);
        themeEntityList.add(themeEntity);
    }

    public void setCafeDomainEntity(CafeDomainEntity cafeDomainEntity) {
        this.cafeDomainEntity = cafeDomainEntity;
        cafeDomainEntity.getCafeEntityList().add(this);
    }
}
