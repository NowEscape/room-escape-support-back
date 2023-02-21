package com.example.roomescapesupportback.controller;

import com.example.roomescapesupportback.service.GenreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @GetMapping("/genres")
  public ResponseEntity<List<String>> getGenre() {

    var result = genreService.getGenreList();

    return ResponseEntity.ok(result);
  }

}
