package com.example.roomescapesupportback.model.response;

import com.example.roomescapesupportback.model.entity.CafeDomainEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CafeResponse {
    private int cafeId;
    private String cafeName;
    private String address;
    private String latitude;
    private String longitude;
    private String region1;
    private String region2;
    private String phoneNumber;
    private String cafeImageUrl;
    private String createdDate;
    private String updatedDate;
    @JsonProperty("isClosed")
    private boolean isClosed;
    private CafeDomainEntity cafeDomainEntity;
}
