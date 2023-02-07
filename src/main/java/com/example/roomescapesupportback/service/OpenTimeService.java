package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.DTO.FilterOption;
import com.example.roomescapesupportback.model.DTO.ThemeWithDate;
import com.example.roomescapesupportback.repository.CafeRepository;
import com.example.roomescapesupportback.repository.GenreRepository;
import com.example.roomescapesupportback.repository.ThemeDateRepository;
import com.example.roomescapesupportback.repository.ThemeRepository;
import com.example.roomescapesupportback.util.ListCustomUtil;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenTimeService {

  private final SearchWordService searchWordService;
  private final CafeRepository cafeRepository;
  private final ThemeRepository themeRepository;

  private final ThemeDateRepository themeDateRepository;
  private final GenreRepository genreRepository;

  public List<ThemeWithDate> getThemeOpenTimeList(FilterOption filterOption) {
    return ObjectUtils.isEmpty(filterOption) ? getThemeOpenTimeListWithoutFilter()
        : getThemeOpenTimeListWithFilter(filterOption);
  }

  public List<ThemeWithDate> getThemeOpenTimeListWithoutFilter() {
    var defaultFilterOption = new FilterOption();

    var themeIdList = filterThemeIdList(defaultFilterOption);

    return themeRepository.findAllWithTimeUsingJoin(themeIdList, defaultFilterOption.getThemeTime()).stream()
        .map(ThemeWithDate::from).toList();
  }

  public List<ThemeWithDate> getThemeOpenTimeListWithFilter(FilterOption filterOption) {

    var themeIdList = filterThemeIdList(filterOption);

    var result = themeRepository.findAllWithTimeUsingJoin(themeIdList, filterOption.getThemeTime());

    return result.stream().map(ThemeWithDate::from).toList();
  }

  public List<Integer> filterThemeIdList(FilterOption filterOption) {
    var themeIdList = new ArrayList<Integer>();

    if (StringUtils.isNotBlank(filterOption.getRegion1())) {
      var themeIdListByRegion =
          StringUtils.isNotBlank(filterOption.getRegion2()) ?
              cafeRepository.findThemeIdByRegion(filterOption.getRegion1(),
                  filterOption.getRegion2())
              : cafeRepository.findThemeIdByRegion(filterOption.getRegion1());

      themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
          themeIdListByRegion);
    }

    if (StringUtils.isNotBlank(filterOption.getGenreName())) {
      var themeIdListByGenre = genreRepository.findThemeIdListByGenre(filterOption.getGenreName());
      themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
          themeIdListByGenre);
    }

    if (StringUtils.isNotBlank(filterOption.getSearchWord())) {
      var themeIdListBySearchWord = searchWordService.findThemeIdListBySearchWord(
          filterOption.getSearchWord());
      themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
          themeIdListBySearchWord);
    }

    if (ObjectUtils.isNotEmpty(filterOption.getThemeTime())) {
      var themeTimeLdt = filterOption.getThemeTime();
      var themeIdListByOpenTime = themeDateRepository.findThemeIdListByThemeTime(
          LocalDateTime.now()
              .isBefore(themeTimeLdt)
              ? themeTimeLdt
              : LocalDateTime.now(),
          themeTimeLdt.withHour(23).withMinute(59).withSecond(59));
      themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
          themeIdListByOpenTime);
    }

    return themeIdList.stream().toList();
  }


  public List<ThemeWithDate> getAllThemeList() {
    return themeRepository.findAllWithTimeUsingLeftJoin().stream()
        .map(ThemeWithDate::from).toList();
  }
}
