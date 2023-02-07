package com.example.roomescapesupportback.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime createdDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime updatedDate;
  private Genre genre;
}
