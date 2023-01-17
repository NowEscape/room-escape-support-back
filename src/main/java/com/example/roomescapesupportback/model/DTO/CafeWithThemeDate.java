package com.example.roomescapesupportback.model.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CafeWithThemeDate {

  private Cafe cafe;
  private List<ThemeWithDate> themeWithDateList;
}
