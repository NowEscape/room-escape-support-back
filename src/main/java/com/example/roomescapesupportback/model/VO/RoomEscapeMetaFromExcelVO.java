package com.example.roomescapesupportback.model.VO;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class RoomEscapeMetaFromExcelVO {
    private String region1;
    private String region2;
    private String domainName;
    private String cafeName;
    private String themeName;
    private String genre;
    private String difficulty;
    private String time;
    private String openDate;
}
