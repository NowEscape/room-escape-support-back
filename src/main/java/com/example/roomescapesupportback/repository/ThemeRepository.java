package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {
    @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
    public List<ThemeEntity> findAllWithTimeUsingLeftJoin();


    @Query(value = "SELECT t FROM ThemeEntity t JOIN FETCH t.themeDateEntityList LEFT JOIN FETCH t.genreEntity")
    public List<ThemeEntity> findAllWithTimeUsingJoin();


    @Query(value = "SELECT t FROM ThemeEntity t LEFT JOIN FETCH t.themeDateEntityList td LEFT JOIN FETCH t.genreEntity WHERE t.themeId = :themeId")
    public ThemeEntity findWithTimeUsingJoinAndThemeIdEquals(int themeId);
}
