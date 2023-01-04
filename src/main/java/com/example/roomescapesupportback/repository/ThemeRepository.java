package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Integer> {
}
