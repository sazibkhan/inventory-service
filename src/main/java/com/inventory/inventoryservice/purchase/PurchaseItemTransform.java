package com.inventory.inventoryservice.purchase;


import com.inventory.inventoryservice.purchase.model.PurchaseItemDto;
import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import com.inventory.inventoryservice.stock.model.StockDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;


public class PurchaseItemTransform{

  public static PurchaseItemEntity toPurchaseItemEntity(PurchaseItemDto purchaseItemDto){
    var entity = new PurchaseItemEntity();
    BeanUtils.copyProperties(purchaseItemDto, entity);
    return entity;
  }


  public static List<StockDto> toStockDto(List<PurchaseItemEntity> items){
    return items.stream()
      .map(itm -> {
        var stockDto = new StockDto();
        stockDto.setProductId(itm.getProductId());
        stockDto.setCurrentStock(itm.getQuantity());
        return stockDto;
      }).collect(Collectors.toList());

  }


}
