package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.DTO.ThemeWithDate;
import com.example.roomescapesupportback.model.entity.ThemeDateEntity;
import com.example.roomescapesupportback.model.entity.ThemeEntity;
import com.example.roomescapesupportback.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ThemeWithDate> getThemeOpenTimeList() {
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
