package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.sales.model.SalesItemDto;
import org.springframework.beans.BeanUtils;

public class SalesItemTransform {

  public static SalesItemEntity toSalesItemEntity(SalesItemDto salesItemDto) {
    var entity = new SalesItemEntity();
    BeanUtils.copyProperties(salesItemDto, entity);
    return entity;
  }
}
