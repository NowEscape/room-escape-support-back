package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.CafeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CafeRepository extends JpaRepository<CafeEntity, Integer> {

  @Query(value = "SELECT c FROM CafeEntity c LEFT JOIN FETCH c.cafeDomainEntity LEFT JOIN FETCH c.themeEntityList")
  public List<CafeEntity> findAllWithThemeUsingJoin();

  @Query(value = "SELECT c FROM CafeEntity c LEFT JOIN FETCH c.cafeDomainEntity LEFT JOIN FETCH c.themeEntityList t LEFT JOIN t.themeDateEntityList")
  public List<CafeEntity> findAllWithThemeAndTimeUsingJoin();

  @Query(value = "SELECT c FROM CafeEntity c LEFT JOIN FETCH c.cafeDomainEntity LEFT JOIN FETCH c.themeEntityList t LEFT JOIN t.themeDateEntityList td WHERE c.cafeId = :cafeId")
  public CafeEntity findWithThemeAndTimeUsingJoinAndCafeIdEquals(int cafeId);
}
