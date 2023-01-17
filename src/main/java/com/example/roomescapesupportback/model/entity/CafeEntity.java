package com.example.roomescapesupportback.model.entity;


import com.example.roomescapesupportback.model.DTO.Cafe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "cafe", indexes = {@Index(name = "cafe_index1", columnList = "cafe_name"),
    @Index(name = "cafe_index2", columnList = "theme_id"),
    @Index(name = "cafe_index3", columnList = "region1, region2")})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cafe_id", nullable = false, unique = true, updatable = false, insertable = false, columnDefinition = "INT(11)")
  private int cafeId;

  @Column(name = "cafe_name", nullable = false)
  private String cafeName;

  @Column(name = "address")
  private String address;

  @Column(name = "latitude")
  private String latitude;

  @Column(name = "longitude")
  private String longitude;

  @Column(name = "region_1", columnDefinition = "varchar(255) comment '시/도'")
  private String region1;

  @Column(name = "region2", columnDefinition = "varchar(255) comment '시/군/구'")
  private String region2;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "cafe_description")
  private String cafeDescription;

  @Column(name = "shot_cut_url")
  private String shotCutUrl;


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

  public Cafe toDto() {
    return Cafe.builder()
        .cafeName(cafeName)
        .cafeImageUrl(cafeImageUrl)
        .address(address)
        .phoneNumber(phoneNumber)
        .cafeDescription(cafeDescription)
        .shotCutUrl(shotCutUrl)
        .build();
  }
}
