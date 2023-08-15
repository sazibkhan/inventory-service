package com.inventory.inventoryservice.utils;

import org.apache.commons.collections4.IteratorUtils;

import java.util.Iterator;
import java.util.List;

public class IterableUtils {

  public static <E> List<E> toList(Iterable<E> iterable) {
    return IteratorUtils.toList(emptyIteratorIfNull(iterable));
  }

  private static <E> Iterator<E> emptyIteratorIfNull(Iterable<E> iterable) {
    return (iterable != null ? iterable.iterator() : IteratorUtils.emptyIterator());
  }

}
