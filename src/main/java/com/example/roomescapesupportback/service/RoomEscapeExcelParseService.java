package com.example.roomescapesupportback.service;

import com.example.roomescapesupportback.model.VO.RoomEscapeMetaFromExcelVO;
import com.example.roomescapesupportback.model.entity.CafeDomainEntity;
import com.example.roomescapesupportback.model.entity.CafeEntity;
import com.example.roomescapesupportback.model.entity.GenreEntity;
import com.example.roomescapesupportback.model.entity.ThemeEntity;
import com.example.roomescapesupportback.repository.CafeDomainRepository;
import com.example.roomescapesupportback.repository.CafeRepository;
import com.example.roomescapesupportback.repository.GenreRepository;
import com.example.roomescapesupportback.repository.ThemeRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.text.DateFormatter;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomEscapeExcelParseService {

  private final DateFormatter dateFormatter = new DateFormatter();
  private final CafeDomainRepository cafeDomainRepository;
  private final CafeRepository cafeRepository;
  private final ThemeRepository themeRepository;
  private final GenreRepository genreRepository;

  @Transactional
  public void doParserRoomEscape() throws IOException {
    log.info("doParser start");
    var file = Optional.ofNullable(
            getClass().getClassLoader().getResourceAsStream("/static/room_escape_meta.xlsx"))
        .orElseThrow(() -> new IOException("file is null"));
    var workbook = new XSSFWorkbook(file);

    var sheet = workbook.getSheetAt(0);

    var failedParseDataList = new ArrayList<XSSFRow>();

    var metaList = new ArrayList<RoomEscapeMetaFromExcelVO>();

    for (int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
      try {
        var row = Optional.ofNullable(sheet.getRow(r))
            .orElseThrow(() -> new Exception("row is null"));
        metaList.add(convertXSSFRowToRoomEscapeMetaFromExcelVO(row));
      } catch (Exception e) {
        failedParseDataList.add(sheet.getRow(r));
        log.error("excelParse error from {}: ", r, e);
      }
    }

    //logging metaList and failedParseDataList to array
    log.info("metaList: {}", ArrayUtils.toString(metaList.toArray()));
    log.info("failedParseDataList: {}", ArrayUtils.toString(failedParseDataList));

    var domainEntityList = new ArrayList<CafeDomainEntity>();
    var cafeEntityList = new ArrayList<CafeEntity>();
    var themeEntityList = new ArrayList<ThemeEntity>();

    metaList.forEach(meta -> {
      if (domainEntityList.stream().map(CafeDomainEntity::getCafeDomainName)
          .noneMatch(domain -> domain.equals(meta.getDomainName()))) {
        domainEntityList.add(
            CafeDomainEntity.builder().cafeDomainName(meta.getDomainName()).isClosed(false)
                .build());
      }
    });

    cafeDomainRepository.saveAll(domainEntityList);

    metaList.forEach(meta -> {
      if (cafeEntityList.stream().filter(
              cafe -> cafe.getCafeDomainEntity().getCafeDomainName().equals(meta.getDomainName()))
          .map(CafeEntity::getCafeName)
          .noneMatch(cafeName -> cafeName.equals(meta.getCafeName()))) {
        cafeEntityList.add(
            CafeEntity.builder()
                .cafeName(meta.getCafeName())
                .isClosed(false)
                .region1(meta.getRegion1())
                .region2(meta.getRegion2())
                .cafeDomainEntity(domainEntityList.stream()
                    .filter(domain -> domain.getCafeDomainName().equals(meta.getDomainName()))
                    .findFirst().orElse(null))
                .build());
      }
    });

    cafeRepository.saveAll(cafeEntityList);

    metaList.forEach(meta -> {
      if (themeEntityList.stream().filter(theme ->
              theme.getCafeEntity().getCafeDomainEntity().getCafeDomainName()
                  .equals(meta.getDomainName()) && theme.getCafeEntity().getCafeName()
                  .equals(meta.getCafeName())).map(ThemeEntity::getThemeName)
          .noneMatch(themeName -> themeName.equals(meta.getThemeName()))) {
        themeEntityList.add(
            ThemeEntity.builder()
                .themeName(meta.getThemeName())
                .isClosed(false)
                .cafeEntity(cafeEntityList.stream().filter(cafe ->
                    cafe.getCafeDomainEntity().getCafeDomainName().equals(meta.getDomainName())
                        && cafe.getCafeName().equals(meta.getCafeName())).findFirst().get())
                .build());
      }
    });

    themeRepository.saveAll(themeEntityList);

    log.info("doParser end");
  }

  private RoomEscapeMetaFromExcelVO convertXSSFRowToRoomEscapeMetaFromExcelVO(XSSFRow row)
      throws Exception {
    var region1 = Optional.ofNullable(row.getCell(0))
        .orElseThrow(() -> new Exception("region1 is null")).getStringCellValue();
    var region2 = Optional.ofNullable(row.getCell(1))
        .orElseThrow(() -> new Exception("region2 is null")).getStringCellValue();
    var domainName = Optional.ofNullable(row.getCell(2))
        .orElseThrow(() -> new Exception("domainName is null")).getStringCellValue();
    var cafeName = Optional.ofNullable(row.getCell(3))
        .orElseThrow(() -> new Exception("cafeName is null")).getStringCellValue();
    var cafeImgUrl = Optional.ofNullable(row.getCell(4))
        .orElseThrow(() -> new Exception("cafeImgUrl is null")).getStringCellValue();
    var phoneNumber = Optional.ofNullable(row.getCell(5))
        .orElseThrow(() -> new Exception("phoneNumber is null")).getStringCellValue();
    var address = Optional.ofNullable(row.getCell(6))
        .orElseThrow(() -> new Exception("address is null")).getStringCellValue();
    var cafeDescription = Optional.ofNullable(row.getCell(7))
        .orElseThrow(() -> new Exception("cafeDescription is null")).getStringCellValue();
    var themeImgUrl = Optional.ofNullable(row.getCell(8))
        .orElseThrow(() -> new Exception("themeImgUrl is null")).getStringCellValue();
    var themeDescription = Optional.ofNullable(row.getCell(9))
        .orElseThrow(() -> new Exception("themeDescription is null")).getStringCellValue();
    var themeName = Optional.ofNullable(row.getCell(10))
        .orElseThrow(() -> new Exception("themeName is null")).getStringCellValue();
    var genre = Optional.ofNullable(row.getCell(12))
        .orElseThrow(() -> new Exception("genre is null")).getStringCellValue();
    var difficulty = Optional.ofNullable(row.getCell(13))
        .orElseThrow(() -> new Exception("difficulty is null")).getStringCellValue();

    return RoomEscapeMetaFromExcelVO.builder()
        .region1(region1)
        .region2(region2)
        .domainName(domainName)
        .cafeName(cafeName)
        .cafeImgUrl(cafeImgUrl)
        .phoneNumber(phoneNumber)
        .address(address)
        .cafeDescription(cafeDescription)
        .themeImgUrl(themeImgUrl)
        .themeDescription(themeDescription)
        .themeName(themeName)
        .genre(genre)
        .difficulty(difficulty)
        .build();
  }


  @Transactional
  public void setMetaData() throws IOException {
    log.info("doParser start");
    var file = Optional.ofNullable(
            getClass().getClassLoader().getResourceAsStream("/static/room_escape_meta.xlsx"))
        .orElseThrow(() -> new IOException("file is null"));
    var workbook = new XSSFWorkbook(file);

    var sheet = workbook.getSheetAt(0);

    var failedParseDataList = new ArrayList<XSSFRow>();

    var metaList = new ArrayList<RoomEscapeMetaFromExcelVO>();

    for (int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
      try {
        var row = Optional.ofNullable(sheet.getRow(r))
            .orElseThrow(() -> new Exception("row is null"));
        metaList.add(convertXSSFRowToRoomEscapeMetaFromExcelVO(row));
      } catch (Exception e) {
        failedParseDataList.add(sheet.getRow(r));
        log.error("excelParse error from {}: ", r, e);
      }
    }

    List<GenreEntity> genreEntityList = genreRepository.findAll();

    //카페 이름을 통해서 db에 저장된 카페 정보를 가져온다. 그리고 그 카페 정보에 엑셀에서 파싱해온 메타 데이터를 저장한다.
    metaList.forEach(meta -> {
      if(StringUtils.isNotBlank(meta.getGenre())){
        var cafeEntity = cafeRepository.findByCafeNameEquals(meta.getCafeName());
        if (StringUtils.isNotBlank(meta.getRegion1()) && StringUtils.isNotBlank(meta.getRegion2())) {
          cafeEntity.setRegion1(meta.getRegion1());
          cafeEntity.setRegion2(meta.getRegion2());
        }
        if (StringUtils.isNotBlank(meta.getCafeImgUrl()) &&
            StringUtils.isNotBlank(meta.getCafeDescription()) &&
            StringUtils.isNotBlank(meta.getPhoneNumber()) &&
            StringUtils.isNotBlank(meta.getAddress())
        ) {
          cafeEntity.setCafeImageUrl(meta.getCafeImgUrl());
          cafeEntity.setPhoneNumber(meta.getPhoneNumber());
          cafeEntity.setAddress(meta.getAddress());
          cafeEntity.setCafeDescription(meta.getCafeDescription());
          cafeRepository.save(cafeEntity);
        }

        var themeEntity = themeRepository.findByThemeNameEqualsAndCafeEntityCafeIdEquals(meta.getThemeName(), cafeEntity.getCafeId());
        if (StringUtils.isNotBlank(meta.getThemeImgUrl()) &&
            StringUtils.isNotBlank(meta.getThemeDescription()) &&
            StringUtils.isNotBlank(meta.getGenre())
        ) {
          themeEntity.setThemeImageUrl(meta.getThemeImgUrl());
          themeEntity.setThemeDescription(meta.getThemeDescription());
          var genreEntity = genreEntityList.stream()
              .filter(genre -> genre.getGenreName().equals(meta.getGenre()))
              .findFirst();
          genreEntity.ifPresent(themeEntity::setGenreEntity);
          themeRepository.save(themeEntity);
        }
      }
    });
  }
}
