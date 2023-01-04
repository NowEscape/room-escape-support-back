package com.example.roomescapesupportback.service;

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
        return themeRepository.findAll();
    }

    public ThemeEntity getTheme(int id) {
        return themeRepository.findById(id).orElse(null);
    }

}
