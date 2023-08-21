package com.inventory.inventoryservice.purchase;


import com.inventory.inventoryservice.purchase.model.PurchaseItemDto;
import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import org.springframework.beans.BeanUtils;


public class PurchaseItemTransform {

    public  static PurchaseItemEntity toPurchaseItemEntity(PurchaseItemDto purchaseItemDto){
        var entity=new PurchaseItemEntity();
        BeanUtils.copyProperties(purchaseItemDto, entity);
        return  entity;
    }







}
