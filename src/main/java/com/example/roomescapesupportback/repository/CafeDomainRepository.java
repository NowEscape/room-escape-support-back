package com.example.roomescapesupportback.repository;

import com.example.roomescapesupportback.model.entity.CafeDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeDomainRepository extends JpaRepository<CafeDomainEntity, Integer> {

    @Query(value = "SELECT cd FROM CafeDomainEntity cd LEFT JOIN FETCH cd.cafeEntityList")
    public List<CafeDomainEntity> findAllWithCafeUsingJoin();

    @Query(value = "SELECT cd FROM CafeDomainEntity cd LEFT JOIN FETCH cd.cafeEntityList c LEFT JOIN c.themeEntityList")
    public List<CafeDomainEntity> findAllWithCafeAndThemeUsingJoin();


    @Query(value = "SELECT cd FROM CafeDomainEntity cd LEFT JOIN cd.cafeEntityList c LEFT JOIN c.themeEntityList t LEFT JOIN t.themeDateEntityList")
    public List<CafeDomainEntity> findAllWithCafeAndThemeAndTimeUsingJoin();
}
