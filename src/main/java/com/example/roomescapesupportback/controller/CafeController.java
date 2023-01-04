package com.example.roomescapesupportback.controller;

import com.example.roomescapesupportback.model.entity.CafeDomainEntity;
import com.example.roomescapesupportback.model.entity.CafeEntity;
import com.example.roomescapesupportback.model.response.CafeDomainResponse;
import com.example.roomescapesupportback.model.response.CafeResponse;
import com.example.roomescapesupportback.service.CafeDomainService;
import com.example.roomescapesupportback.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeDomainService cafeDomainService;
    private final CafeService cafeService;

    private final ModelMapper modelMapper = new ModelMapper();

    //get cafe domain
    @GetMapping(value = "/cafe-domains", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CafeDomainResponse>> getCafeDomainList() {

        var result = cafeDomainService.getCafeDomain().stream().map(entity->modelMapper.map(entity, CafeDomainResponse.class)).toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/cafes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<CafeResponse>> getCafeInfo() {
        var result = cafeService.getCafe().stream().map(entity->modelMapper.map(entity, CafeResponse.class)).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/cafes/{cafeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CafeResponse> getCafeInfo(@PathVariable int cafeId) {
        return ResponseEntity.ok(modelMapper.map(cafeService.getCafe(cafeId), CafeResponse.class));
    }
}
