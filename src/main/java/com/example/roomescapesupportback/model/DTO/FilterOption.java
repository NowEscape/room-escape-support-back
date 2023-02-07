package com.example.roomescapesupportback.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.DateUtil;

@Setter
@Getter
public class FilterOption {

  private String region1;
  private String region2;
  private String genreName;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime themeTime = LocalDateTime.now();
  private String searchWord;
}
