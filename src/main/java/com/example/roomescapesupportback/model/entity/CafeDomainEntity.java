package com.example.roomescapesupportback.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
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
import lombok.ToString;

@Entity
@Table(name = "cafe_domain", indexes = {
    @Index(name = "cafe_domain_index1", columnList = "cafe_domain_name")})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeDomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cafe_domain_id")
  private int cafeDomainId;

  @Column(name = "cafe_domain_name")
  private String cafeDomainName;

  @Column(name = "url")
  private String url;

  @Column(name = "thumbnail_url")
  private String thumbnailUrl;

  @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime updatedDate;

  @Column(name = "is_closed", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
  private boolean isClosed;

  @JsonIgnore
  @OneToMany(mappedBy = "cafeDomainEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CafeEntity> cafeEntityList = new ArrayList<>();

  public void addCafeEntity(CafeEntity cafeEntity) {
    cafeEntity.setCafeDomainEntity(this);
    cafeEntityList.add(cafeEntity);
  }
}
