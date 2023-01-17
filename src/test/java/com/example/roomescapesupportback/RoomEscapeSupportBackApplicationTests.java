package com.example.roomescapesupportback;

import com.fasterxml.jackson.databind.MapperFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootTest
@Slf4j
class RoomEscapeSupportBackApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        var objectMapper = Jackson2ObjectMapperBuilder.json().autoDetectGettersSetters(false).build();

        log.info(objectMapper.getVisibilityChecker().toString());
        Assertions.assertTrue(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_GETTERS));
        Assertions.assertTrue(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_FIELDS));
        Assertions.assertTrue(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_CREATORS));
    }
}
