package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.entity.CafeEntity;
import com.example.roomescapesupportback.repository.CafeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeService {

  private final CafeRepository cafeRepository;

  public List<CafeEntity> getCafe() {
    return cafeRepository.findAllWithThemeAndTimeUsingJoin();
  }

  public CafeEntity getCafe(int id) {
    return cafeRepository.findWithThemeAndTimeUsingJoinAndCafeIdEquals(id);
  }
}
