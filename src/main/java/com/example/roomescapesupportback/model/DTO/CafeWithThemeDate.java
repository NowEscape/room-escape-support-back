package com.example.roomescapesupportback.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class CafeWithThemeDate {
    private Cafe cafe;
    private List<ThemeWithDate> themeWithDateList;
}
