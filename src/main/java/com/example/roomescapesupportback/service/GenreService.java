package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.repository.GenreRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

  private final GenreRepository genreRepository;

  public List<String> getGenreList() {
    return genreRepository.findAllByThemeEntityListIsCloseIsFalse();
  }
}
