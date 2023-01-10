package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.ThemeDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeDateRepository extends JpaRepository<ThemeDateEntity, Integer> {
}
