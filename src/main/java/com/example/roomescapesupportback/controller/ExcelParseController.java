package com.example.roomescapesupportback.controller;

import com.example.roomescapesupportback.service.RoomEscapeExcelParseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


//TODO: 내부에서만 접근가능하게 해야 함.
@RestController
@RequiredArgsConstructor
@Slf4j
public class ExcelParseController {

  private final RoomEscapeExcelParseService roomEscapeExcelParseService;

  @PutMapping("/excel")
  public ResponseEntity<Boolean> excelParse() {

    try {
      roomEscapeExcelParseService.doParserRoomEscape();
    } catch (Exception e) {
      log.error("excelParse error", e);
      return ResponseEntity.internalServerError().build();
    }

    return ResponseEntity.ok(Boolean.TRUE);
  }
}
