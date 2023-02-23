package com.example.roomescapesupportback.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class RoomEscapeMetaFromExcelVO {

  private String region1;
  private String region2;
  private String domainName;
  private String cafeImgUrl;
  private String phoneNumber;
  private String address;
  private String cafeDescription;
  private String themeImgUrl;
  private String themeDescription;
  private String cafeName;
  private String themeName;
  private String genre;
  private String difficulty;
  private String time;
}
