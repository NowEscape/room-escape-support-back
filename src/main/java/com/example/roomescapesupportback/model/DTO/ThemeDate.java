package com.example.roomescapesupportback.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ThemeDate implements Serializable {
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime themeTime;

  private Boolean isOpen;
}
