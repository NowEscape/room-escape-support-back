package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.entity.CafeDomainEntity;
import com.example.roomescapesupportback.repository.CafeDomainRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeDomainService {

  private final CafeDomainRepository cafeDomainRepository;

  //get cafeDomain from repository
  public List<CafeDomainEntity> getCafeDomain() {
    return cafeDomainRepository.findAllWithCafeAndThemeAndTimeUsingJoin();
  }
}
