package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {
    @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList")
    public List<ThemeEntity> findAllWithTimeUsingJoin();

    @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList td WHERE t.themeId = :themeId")
    public ThemeEntity findWithTimeUsingJoinAndThemeIdEquals(int themeId);
}
