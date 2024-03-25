package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationItemDto;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationItemEntity;
import com.inventory.inventoryservice.stock.model.StockDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ReconciliationItemTransform {

    public  static ReconciliationItemEntity toReconciliationItemEntity(ReconciliationItemDto reconciliationItemDto){
        var entity=new ReconciliationItemEntity();
        BeanUtils.copyProperties(reconciliationItemDto, entity);
        return  entity;
    }


    public static List<StockDto> toStockDto(List<ReconciliationItemEntity> items) {
        return items.stream()
                .map(itm-> {
                    var stockDto = new StockDto();
                    stockDto.setProductId(itm.getProductId());
                    stockDto.setCurrentStock(itm.getQuantity());
                    return stockDto;
                }).collect(Collectors.toList());

    }



}
