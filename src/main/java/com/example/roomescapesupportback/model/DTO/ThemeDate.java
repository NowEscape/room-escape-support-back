package com.example.roomescapesupportback.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@Builder
public class ThemeDate {
    private int themeDateId;
    private ZonedDateTime themeTime;
}
