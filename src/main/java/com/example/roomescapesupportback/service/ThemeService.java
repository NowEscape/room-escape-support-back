package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.DTO.FilterOption;
import com.example.roomescapesupportback.model.DTO.ThemeWithDate;
import com.example.roomescapesupportback.model.entity.ThemeDateEntity;
import com.example.roomescapesupportback.model.entity.ThemeEntity;
import com.example.roomescapesupportback.repository.ThemeRepository;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeService {

  private final ThemeRepository themeRepository;

  public List<ThemeEntity> getTheme() {
    return themeRepository.findAllWithTimeUsingLeftJoin();
  }

  public ThemeEntity getTheme(int id) {
    return themeRepository.findWithTimeUsingJoinAndThemeIdEquals(id);
  }

  public List<ThemeWithDate> getThemeOpenTimeList(FilterOption filterOption) {

    var themeIdListByRegion = themeRepository.findThemeIdListByRegion(filterOption.getRegion1(),
        filterOption.getRegion2());
    var themeIdListByGenre = themeRepository.findThemeIdListByGenre(filterOption.getGenreName());
    var themeIdListByOpenTime = themeRepository.findThemeIdListByThemeTimeBetween(
        ZonedDateTime.now().isBefore(filterOption.getThemeTime()) ? filterOption.getThemeTime()
            : ZonedDateTime.now(),
        filterOption.getThemeTime().withHour(23).withMinute(59).withSecond(59));

    return themeRepository.findAllWithTimeUsingJoin().stream()
        .map(themeEntity ->
            ThemeWithDate.builder()
                .theme(themeEntity.toDto())
                .ThemeDateList(themeEntity.getThemeDateEntityList().stream()
                    .map(ThemeDateEntity::toDto)
                    .toList())
                .cafeName(themeEntity.getCafeEntity().getCafeName())
                .build()
        ).toList();
  }

  public List<ThemeWithDate> getThemeOpenTimeListUsingLeftJoin() {
    return themeRepository.findAllWithTimeUsingLeftJoin().stream()
        .map(themeEntity ->
            ThemeWithDate.builder()
                .theme(themeEntity.toDto())
                .ThemeDateList(themeEntity.getThemeDateEntityList().stream()
                    .map(ThemeDateEntity::toDto)
                    .toList())
                .cafeName(themeEntity.getCafeEntity().getCafeName())
                .build()
        ).toList();
  }
}
