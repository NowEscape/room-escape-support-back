package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {

  @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
  public List<ThemeEntity> findAllWithTimeUsingLeftJoin();


  @Query(value = "SELECT t FROM ThemeEntity t JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
  public List<ThemeEntity> findAllWithTimeUsingJoin();

  @Query(value = "SELECT t.themeId FROM ThemeEntity t JOIN FETCH t.cafeEntity c WHERE c.region1 = :region1 AND c.region2 = :region2")
  public List<Integer> findThemeIdListByRegion(String region1, String region2);

  @Query(value = "SELECT t.themeId FROM ThemeEntity t JOIN FETCH t.genreEntity g WHERE g.genreName = :genreName")
  public List<Integer> findThemeIdListByGenre(String genreName);

  @Query(value = "SELECT t.themeId FROM ThemeEntity t JOIN FETCH t.themeDateEntityList td WHERE td.themeTime >= :themeTimeStart AND td.themeTime <= :themeTimeEnd")
  public List<Integer> findThemeIdListByThemeTimeBetween(ZonedDateTime themeTimeStart,
      ZonedDateTime themeTimeEnd);

  @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList td LEFT JOIN FETCH t.genreEntity WHERE t.themeId = :themeId")
  public ThemeEntity findWithTimeUsingJoinAndThemeIdEquals(int themeId);
}
