package com.example.roomescapesupportback.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortOption {
  private boolean isSort = true;

  //static method get defaultSortOption
  public static SortOption getDefaultSortOption() {
    return new SortOption();
  }
}


