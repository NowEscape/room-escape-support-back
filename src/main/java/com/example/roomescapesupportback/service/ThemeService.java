package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.DTO.FilterOption;
import com.example.roomescapesupportback.model.DTO.ThemeWithDate;
import com.example.roomescapesupportback.model.entity.ThemeDateEntity;
import com.example.roomescapesupportback.model.entity.ThemeEntity;
import com.example.roomescapesupportback.repository.CafeRepository;
import com.example.roomescapesupportback.repository.GenreRepository;
import com.example.roomescapesupportback.repository.ThemeDateRepository;
import com.example.roomescapesupportback.repository.ThemeRepository;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeService {

  private final CafeRepository cafeRepository;
  private final ThemeRepository themeRepository;

  private final ThemeDateRepository themeDateRepository;
  private final GenreRepository genreRepository;

  public List<ThemeEntity> getTheme() {
    return themeRepository.findAllWithTimeUsingLeftJoin();
  }

  public ThemeEntity getTheme(int id) {
    return themeRepository.findWithTimeUsingJoinAndThemeIdEquals(id);
  }

  public List<ThemeWithDate> getThemeOpenTimeList(FilterOption filterOption) {

    var themeIdList = new LinkedHashSet<Integer>();
    if (filterOption != null) {

      var themeIdListByRegion = cafeRepository.findThemeIdByRegion(filterOption.getRegion1(),
          filterOption.getRegion2());
      var themeIdListByGenre = genreRepository.findThemeIdListByGenre(filterOption.getGenreName());
      var themeIdListByOpenTime = themeDateRepository.findThemeIdListByThemeTime(
          ZonedDateTime.now()
              .isBefore(filterOption.getThemeTime().withZoneSameLocal(ZoneId.of("UTC")))
              ? filterOption.getThemeTime()
              : ZonedDateTime.now(),
          filterOption.getThemeTime().withHour(23).withMinute(59).withSecond(59));

      themeIdList.addAll(themeIdListByRegion);
      themeIdList.addAll(themeIdListByGenre);
      themeIdList.addAll(themeIdListByOpenTime);

    }

    return themeRepository.findAllWithTimeUsingJoin(themeIdList.stream().toList()).stream()
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
