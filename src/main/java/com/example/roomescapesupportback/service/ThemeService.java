package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import com.example.roomescapesupportback.repository.ThemeRepository;
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
}
