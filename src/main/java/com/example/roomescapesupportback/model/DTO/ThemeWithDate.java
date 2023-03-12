package com.example.roomescapesupportback.model.DTO;

import com.example.roomescapesupportback.model.entity.ThemeDateEntity;
import com.example.roomescapesupportback.model.entity.ThemeEntity;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
@ToString
@Builder
@Slf4j
public class ThemeWithDate implements Serializable {

  private String cafeName;
  private String shortCutUrl;
  private Theme theme;
  private List<ThemeDate> themeDateList;

  public static ThemeWithDate from(ThemeEntity themeEntity) {
    if (CollectionUtils.isEmpty(themeEntity.getThemeDateEntityList())) {
      log.info("themeDateEntityList is empty", themeEntity);
      return ThemeWithDate.builder()
          .theme(themeEntity.toDto())
          .cafeName(themeEntity.getCafeEntity().getCafeName())
          .shortCutUrl(themeEntity.getCafeEntity().getShortCutUrl())
          .build();
    }
    log.info("themeDateEntityList is not empty", themeEntity);
    return ThemeWithDate.builder()
        .theme(themeEntity.toDto())
        .themeDateList(themeEntity.getThemeDateEntityList().stream().map(ThemeDateEntity::toDto)
            .toList())
        .cafeName(themeEntity.getCafeEntity().getCafeName())
        .shortCutUrl(themeEntity.getCafeEntity().getShortCutUrl())
        .build();
  }
}
