package com.example.roomescapesupportback.controller;

import com.example.roomescapesupportback.model.DTO.Region;
import com.example.roomescapesupportback.service.CafeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegionController {

  private final CafeService cafeService;
  @GetMapping("/regions")
  public ResponseEntity<List<Region>> getRegion(){

    var result = cafeService.getRegionList();

    return ResponseEntity.ok(result);
  }
}
