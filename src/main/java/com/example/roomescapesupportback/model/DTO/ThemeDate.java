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
public class ThemeDate {

  private int themeDateId;
  private ZonedDateTime themeTime;

  private boolean isOpen;
}
