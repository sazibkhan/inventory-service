package com.inventory.inventoryservice.purchase.model;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PurchaseItemDto {

    private Long   productId;
    private Integer quantity;
    private Double purchasePrice;
    private Double salesPrice;


}
