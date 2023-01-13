package com.example.roomescapesupportback.model.DTO;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private Integer genreId;
    private String genreName;
}
