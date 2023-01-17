package com.example.roomescapesupportback.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Cafe {

  private int cafeId;
  private String cafeName;
  private String address;
  private String phoneNumber;
  private String cafeImageUrl;
  private String cafeDescription;
  private String shotCutUrl;
}
