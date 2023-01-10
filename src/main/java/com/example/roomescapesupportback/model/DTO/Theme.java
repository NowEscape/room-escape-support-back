package com.example.roomescapesupportback.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@Builder
public class Theme {
    private int themeId;
    private String themeName;
    private String themeDescription;
    private String themeImageUrl;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
    private ZonedDateTime themeOpenDate;
}
