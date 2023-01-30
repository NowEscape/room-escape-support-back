package com.example.roomescapesupportback.util;

import java.util.List;
import org.apache.commons.collections4.ListUtils;

public class ListCustomUtil {
  public static <T> List<T> intersectionIgnoreEmpty(List<T> list1, List<T> list2) {
    if (list1.isEmpty()) {
      return list2;
    }
    if (list2.isEmpty()) {
      return list1;
    }
    return ListUtils.intersection(list1, list2);
  }
}
