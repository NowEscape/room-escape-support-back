package com.example.roomescapesupportback.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelParseService {

    private final DateFormatter dateFormatter = new DateFormatter();

    public void doParser() throws IOException {
        log.info("doParser start");
        var file = new FileInputStream("../../../src/main/resources/static/room_escape_meta.xlsx");
        var workbook = new XSSFWorkbook(file);

        var sheet = workbook.getSheetAt(0);

        var failedList = new ArrayList<XSSFRow>();

        for (int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
            try {
                var row = Optional.ofNullable(sheet.getRow(r)).orElseThrow(() -> new Exception("row is null"));

                for(int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
                    var cell = Optional.ofNullable(row.getCell(c)).orElseThrow(() -> new Exception("cell is null"));



                }

            } catch (Exception e) {
                failedList.add(sheet.getRow(r));
                log.error("excelParse error", e);
            }

        }
    }
}
