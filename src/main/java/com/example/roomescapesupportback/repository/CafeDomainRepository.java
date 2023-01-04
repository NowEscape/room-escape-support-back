package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.CafeDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeDomainRepository extends JpaRepository<CafeDomainEntity, Integer> {
}
