package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.reconciliation.model.ReconciliationItemEntity;
import com.inventory.inventoryservice.sales.model.SalesItemDto;
import com.inventory.inventoryservice.sales.model.SalesItemEntity;
import com.inventory.inventoryservice.stock.model.StockDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SalesItemTransform {

  public static SalesItemEntity toSalesItemEntity(SalesItemDto salesItemDto) {
    var entity = new SalesItemEntity();
    BeanUtils.copyProperties(salesItemDto, entity);
    return entity;
  }

  public static List<StockDto> toStockDto(List<SalesItemEntity> items) {
    return items.stream()
      .map(itm -> {
        var stockDto = new StockDto();
        stockDto.setProductId(itm.getProductId());
        stockDto.setCurrentStock(itm.getQuantity());
        return stockDto;
      }).collect(Collectors.toList());

  }
}
