package com.example.roomescapesupportback.util;

import java.util.List;
import org.apache.commons.collections4.ListUtils;

public class ListCustomUtil {

  public static <T> List<T> intersectionIgnoreEmptySource(final List<T> source,
      final List<T> target) {
    if (source.isEmpty()) {
      return target;
    }
    return ListUtils.intersection(source, target);
  }
}
