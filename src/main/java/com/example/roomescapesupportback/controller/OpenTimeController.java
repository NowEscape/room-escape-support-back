package com.example.roomescapesupportback.controller;

import com.example.roomescapesupportback.model.DTO.ThemeWithDate;
import com.example.roomescapesupportback.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenTimeController {
    private final ThemeService themeService;


    @GetMapping("/openTimeThemeList")
    public ResponseEntity<List<ThemeWithDate>> getOpenTimeThemeList() {
        return ResponseEntity.ok(themeService.getThemeOpenTimeList());
    }

    @GetMapping("/allThemeList")
    public ResponseEntity<List<ThemeWithDate>> getAllThemeList() {
        return ResponseEntity.ok(themeService.getThemeOpenTimeListUsingLeftJoin());
    }
}
