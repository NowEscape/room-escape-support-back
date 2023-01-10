package com.example.roomescapesupportback.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CafeDomain {
    private int cafeDomainId;
    private String cafeDomain;
    private String url;
    private String thumbnailUrl;
    private String createdDate;
    private String updatedDate;

    @JsonProperty("isClosed")
    private boolean isClosed;
}
