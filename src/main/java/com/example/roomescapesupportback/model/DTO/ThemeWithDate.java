package com.example.roomescapesupportback.model.DTO;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ThemeWithDate {

  private String cafeName;
  private Theme theme;
  private List<ThemeDate> ThemeDateList;
}
