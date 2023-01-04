package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.CafeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<CafeEntity, Integer> {
}
