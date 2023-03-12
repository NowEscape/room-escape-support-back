package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.DTO.FilterOption;
import com.example.roomescapesupportback.model.DTO.ThemeWithDate;
import com.example.roomescapesupportback.model.SortOption;
import com.example.roomescapesupportback.repository.CafeRepository;
import com.example.roomescapesupportback.repository.GenreRepository;
import com.example.roomescapesupportback.repository.ThemeDateRepository;
import com.example.roomescapesupportback.repository.ThemeRepository;
import com.example.roomescapesupportback.util.ListCustomUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    return getThemeOpenTimeListWithFilter(defaultFilterOption);
  }

  public List<ThemeWithDate> getThemeOpenTimeListWithFilter(FilterOption filterOption) {

    var themeIdList = getThemeIdList(filterOption);

    var result = themeRepository.findAllWithTimeUsingJoin(themeIdList, filterOption.getThemeTime());

    result.sort((o1, o2) -> {
      var o1Index = themeIdList.indexOf(o1.getThemeId());
      var o2Index = themeIdList.indexOf(o2.getThemeId());
      return o1Index - o2Index;
    });

    return result.stream().map(ThemeWithDate::from).toList();
  }

  public List<Integer> getThemeIdList(FilterOption filterOption) {
    return getThemeIdList(filterOption, new SortOption());
  }

  public List<Integer> getThemeIdList(FilterOption filterOption, SortOption sortOption) {
    var themeIdList = new ArrayList<Integer>();

    if (StringUtils.isNotBlank(filterOption.getRegion1())) {
      var themeIdListByRegion =
          StringUtils.isNotBlank(filterOption.getRegion2()) ?
              cafeRepository.findThemeIdByRegion(filterOption.getRegion1(),
                  filterOption.getRegion2())
              : cafeRepository.findThemeIdByRegion(filterOption.getRegion1());

      themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
          themeIdListByRegion);

      if (CollectionUtils.isEmpty(themeIdList)) {
        return List.of();
      }
    }

    if (StringUtils.isNotBlank(filterOption.getGenreName())) {
      var themeIdListByGenre = genreRepository.findThemeIdListByGenre(filterOption.getGenreName());

      themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
          themeIdListByGenre);

      if (CollectionUtils.isEmpty(themeIdList)) {
        return List.of();
      }
    }

    if (StringUtils.isNotBlank(filterOption.getSearchWord())) {
      var themeIdListBySearchWord = searchWordService.findThemeIdListBySearchWord(
          filterOption.getSearchWord());

      themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
          themeIdListBySearchWord);

      if (CollectionUtils.isEmpty(themeIdList)) {
        return List.of();
      }
    }

    var themeTimeLdt = Objects.requireNonNull(filterOption.getThemeTime());

    var themeIdListByOpenTime = themeRepository.findThemeIdListByThemeTime(
        LocalDateTime.now()
            .isBefore(themeTimeLdt)
            ? themeTimeLdt
            : LocalDateTime.now(),
        themeTimeLdt.withHour(23).withMinute(59).withSecond(59));

    themeIdList = (ArrayList<Integer>) ListCustomUtil.intersectionIgnoreEmptySource(themeIdList,
        themeIdListByOpenTime);

    if (CollectionUtils.isEmpty(themeIdList)) {
      return List.of();
    }

    if (sortOption.isSort()) {
      //sort themeIdList by themeTime
      themeIdList.sort((o1, o2) -> {
        var o1ThemeTime = themeIdListByOpenTime.indexOf(o1);
        var o2ThemeTime = themeIdListByOpenTime.indexOf(o2);
        return o1ThemeTime - o2ThemeTime;
      });
    }

    return themeIdList.stream().toList();
  }


  public List<ThemeWithDate> getAllThemeList() {
    return themeRepository.findAllWithTimeUsingLeftJoin().stream()
        .map(ThemeWithDate::from).toList();
  }
}
