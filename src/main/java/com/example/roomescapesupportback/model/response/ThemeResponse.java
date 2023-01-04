package com.example.roomescapesupportback.model.response;

import com.example.roomescapesupportback.model.entity.CafeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ThemeResponse {
    private int themeId;
    private String themeName;
    private String themeDescription;
    private String themeImageUrl;
    private String createdDate;
    private String updatedDate;
    @JsonProperty("isClosed")
    private boolean isClosed;
    private CafeEntity cafeEntity;
}
