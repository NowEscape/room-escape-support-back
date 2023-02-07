package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeDateEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeDateRepository extends JpaRepository<ThemeDateEntity, Integer> {

  @Query(value = "SELECT t.themeId FROM ThemeDateEntity td JOIN td.themeEntity t WHERE td.isOpen = true")
  public List<Integer> findThemeIdListByThemeTime(LocalDateTime themeTimeStart,
      LocalDateTime themeTimeEnd);
}
