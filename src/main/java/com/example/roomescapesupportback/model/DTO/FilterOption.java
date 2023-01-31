package com.example.roomescapesupportback.model.DTO;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilterOption {

  private String region1;
  private String region2;
  private String genreName;
  private ZonedDateTime themeTime = ZonedDateTime.now();
  private String searchWord;
}
