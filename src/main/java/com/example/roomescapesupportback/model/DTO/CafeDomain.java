package com.example.roomescapesupportback.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CafeDomain {

  private String cafeDomain;
  private String url;
  private String thumbnailUrl;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime createdDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime updatedDate;

  @JsonProperty("isClosed")
  private Boolean isClosed;
}
