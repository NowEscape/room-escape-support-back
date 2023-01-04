package com.example.roomescapesupportback.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CafeDomainResponse {
    private int cafeDomainId;
    private String cafeDomain;
    private String url;
    private String thumbnailUrl;
    private String createdDate;
    private String updatedDate;

    @JsonProperty("isClosed")
    private boolean isClosed;
}
