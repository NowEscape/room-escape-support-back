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
  private String shotCutUrl;
  private Theme theme;
  private List<ThemeDate> themeDateList;

  public static ThemeWithDate from(ThemeEntity themeEntity) {
    return ThemeWithDate.builder()
        .theme(themeEntity.toDto())
        .themeDateList(themeEntity.getThemeDateEntityList().stream()
            .map(ThemeDateEntity::toDto)
            .toList())
        .cafeName(themeEntity.getCafeEntity().getCafeName())
        .shotCutUrl(themeEntity.getCafeEntity().getShotCutUrl())
        .build();
  }
}
