package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.GenreEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
  @Query(value = "SELECT t.themeId FROM GenreEntity g JOIN g.themeEntityList t WHERE g.genreName = :genreName")
  public List<Integer> findThemeIdListByGenre(String genreName);

  @Query(value = "SELECT distinct g.genreName FROM GenreEntity g JOIN g.themeEntityList t WHERE t.isClosed = false")
  public List<String> findAllByThemeEntityListIsCloseIsFalse();
}
