package com.inventory.inventoryservice.purchase.model;

import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
public class PurchaseItemDto {

    private Long   productId;
    private Double purchasePrice;
    private Double  salesPrice;


}
