package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import com.example.roomescapesupportback.repository.CafeRepository;
import com.example.roomescapesupportback.repository.ThemeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchWordService {

  private final ThemeRepository themeRepository;
  private final CafeRepository cafeRepository;

  public List<Integer> findThemeIdListBySearchWord(String searchWord) {
    var themeIdList = new ArrayList<Integer>();

    themeIdList.addAll(
        themeRepository.findByThemeNameContaining(searchWord)
            .stream().map(ThemeEntity::getThemeId).toList()
    );
    themeIdList.addAll(
        cafeRepository.findThemeIdByCafeNameLike(makeSearchWord(searchWord))
    );

    return themeIdList;
  }
  public String makeSearchWord(String searchWord) {
    return "%" + searchWord + "%";
  }
}
