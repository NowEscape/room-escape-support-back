package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {

  @Query(value = "SELECT t FROM ThemeEntity t WHERE t.themeName = :themeName AND t.cafeEntity.cafeId = :cafeId")
  public ThemeEntity findByThemeNameEqualsAndCafeEntityCafeIdEquals(String themeName, int cafeId);

  @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
  public List<ThemeEntity> findAllWithTimeUsingLeftJoin();


  @Query(value = "SELECT distinct t FROM ThemeEntity t JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
  public List<ThemeEntity> findAllWithTimeUsingJoin();

  @Query(value = "SELECT distinct t FROM ThemeEntity t JOIN FETCH t.themeDateEntityList td LEFT JOIN FETCH t.genreEntity WHERE t.themeId IN :themeIdList AND DATE_FORMAT(td.themeTime,'%y-%m-%d') = DATE_FORMAT(:themeTime,'%y-%m-%d')")
  public List<ThemeEntity> findAllWithTimeUsingJoin(List<Integer> themeIdList, LocalDateTime themeTime);


  @Query(value = "SELECT distinct t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList td LEFT JOIN FETCH t.genreEntity WHERE t.themeId = :themeId")
  public ThemeEntity findWithTimeUsingJoinAndThemeIdEqualsAndThemeTimeInNowDate(int themeId);
  public List<ThemeEntity> findByThemeNameContaining(String themeName);
}
