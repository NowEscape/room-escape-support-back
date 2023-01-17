package com.example.roomescapesupportback.model.DTO;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Theme {

  private String themeName;
  private String themeDescription;
  private String themeImageUrl;
  private ZonedDateTime createdDate;
  private ZonedDateTime updatedDate;
  private ZonedDateTime themeOpenDate;
  private Genre genre;
}
