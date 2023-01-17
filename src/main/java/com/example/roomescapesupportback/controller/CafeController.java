package com.example.roomescapesupportback.controller;

import com.example.roomescapesupportback.model.DTO.Cafe;
import com.example.roomescapesupportback.service.CafeDomainService;
import com.example.roomescapesupportback.service.CafeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CafeController {

  private final CafeDomainService cafeDomainService;
  private final CafeService cafeService;

  private final ModelMapper modelMapper = new ModelMapper();

  @GetMapping(value = "/cafes", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<Cafe>> getCafeInfo() {
    var result = cafeService.getCafe().stream().map(entity -> modelMapper.map(entity, Cafe.class))
        .toList();
    return ResponseEntity.ok(result);
  }

  @GetMapping(value = "/cafes/{cafeId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Cafe> getCafeInfo(@PathVariable int cafeId) {
    return ResponseEntity.ok(modelMapper.map(cafeService.getCafe(cafeId), Cafe.class));
  }
}
