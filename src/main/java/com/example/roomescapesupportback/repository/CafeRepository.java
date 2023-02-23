package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.DTO.Region;
import com.example.roomescapesupportback.model.entity.CafeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CafeRepository extends JpaRepository<CafeEntity, Integer> {

  public CafeEntity findByCafeNameEquals(String cafeName);

  @Query(value = "SELECT c FROM CafeEntity c LEFT JOIN FETCH c.cafeDomainEntity LEFT JOIN FETCH c.themeEntityList")
  public List<CafeEntity> findAllWithThemeUsingJoin();

  @Query(value = "SELECT c FROM CafeEntity c LEFT JOIN FETCH c.cafeDomainEntity LEFT JOIN FETCH c.themeEntityList t LEFT JOIN t.themeDateEntityList")
  public List<CafeEntity> findAllWithThemeAndTimeUsingJoin();

  @Query(value = "SELECT c FROM CafeEntity c LEFT JOIN FETCH c.cafeDomainEntity LEFT JOIN FETCH c.themeEntityList t LEFT JOIN t.themeDateEntityList td WHERE c.cafeId = :cafeId")
  public CafeEntity findWithThemeAndTimeUsingJoinAndCafeIdEquals(int cafeId);

  @Query(value = "SELECT t.themeId FROM CafeEntity c JOIN c.themeEntityList t WHERE c.region1 = :region1")
  public List<Integer> findThemeIdByRegion(String region1);

  @Query(value = "SELECT t.themeId FROM CafeEntity c JOIN c.themeEntityList t WHERE c.region1 = :region1 AND c.region2 = :region2")
  public List<Integer> findThemeIdByRegion(String region1, String region2);

  @Query(value = "SELECT t.themeId FROM CafeEntity c JOIN c.themeEntityList t WHERE c.cafeName like :cafeName")
  public List<Integer> findThemeIdByCafeNameLike(String cafeName);

  @Query(value = "SELECT distinct c FROM CafeEntity c WHERE c.isClosed = false GROUP BY c.region1, c.region2")
  public List<CafeEntity> findAllRegionByIsClosedIsFalse();
}
