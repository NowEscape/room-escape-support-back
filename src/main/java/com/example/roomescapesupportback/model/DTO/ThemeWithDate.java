package com.example.roomescapesupportback.model.DTO;

import com.example.roomescapesupportback.model.entity.ThemeDateEntity;
import com.example.roomescapesupportback.model.entity.ThemeEntity;
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

  public static ThemeWithDate from(ThemeEntity themeEntity) {
    return ThemeWithDate.builder()
        .theme(themeEntity.toDto())
        .ThemeDateList(themeEntity.getThemeDateEntityList().stream()
            .map(ThemeDateEntity::toDto)
            .toList())
        .cafeName(themeEntity.getCafeEntity().getCafeName())
        .build();
  }
}
