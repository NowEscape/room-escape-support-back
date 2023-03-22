package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {

  @Query(value = "SELECT t FROM ThemeEntity t WHERE t.themeName = :themeName AND t.cafeEntity.cafeId = :cafeId")
  public ThemeEntity findByThemeNameEqualsAndCafeEntityCafeIdEquals(String themeName, int cafeId);


  @Query(value = "SELECT t.theme_id FROM theme t"
      + " LEFT JOIN theme_date td ON t.theme_id = td.theme_id"
      + " WHERE IF(td.theme_date_id is null,true, td.is_open)"
      + " GROUP BY t.theme_id\n"
      + " ORDER BY IF(count(td.theme_date_id) = 0, 10000000, count(td.theme_date_id))", nativeQuery = true)
  public List<Integer> findThemeIdListByThemeTime(LocalDateTime themeTimeStart,
      LocalDateTime themeTimeEnd);

  @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
  public List<ThemeEntity> findAllWithTimeUsingLeftJoin();


  @Query(value = "SELECT distinct t FROM ThemeEntity t JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
  public List<ThemeEntity> findAllWithTimeUsingJoin();

  @Query(value = "SELECT distinct t FROM ThemeEntity t"
      + " LEFT JOIN t.themeDateEntityList td on DATE_FORMAT(td.themeTime,'%y-%m-%d') = DATE_FORMAT(:themeTime,'%y-%m-%d') AND td.isOpen = true"
      + " LEFT JOIN FETCH t.genreEntity"
      + " WHERE t.themeId IN :themeIdList AND t.isClosed = false"
      + " ORDER BY td.themeTime ASC")
  public List<ThemeEntity> findAllWithTimeUsingJoin(List<Integer> themeIdList, LocalDateTime themeTime);


  @Query(value = "SELECT distinct t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList td LEFT JOIN FETCH t.genreEntity WHERE t.themeId = :themeId")
  public ThemeEntity findWithTimeUsingJoinAndThemeIdEqualsAndThemeTimeInNowDate(int themeId);
  public List<ThemeEntity> findByThemeNameContaining(String themeName);
}
