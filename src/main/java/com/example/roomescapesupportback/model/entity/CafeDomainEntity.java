package com.example.roomescapesupportback.model.entity;


import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "cafe_domain")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeDomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_domain_id")
    private int cafeDomainId;

    @Column(name = "cafe_domain_name")
    private String cafeDomain;

    @Column(name = "url")
    private String url;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime createdDate;

    @Column(name = "updated_date", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime updatedDate;

    @Column(name = "is_closed", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isClosed;
}
