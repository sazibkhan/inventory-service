package com.inventory.inventoryservice.sales.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesItemDto {

  private Long productId;
  private Double purchasePrice;
  private Double salesPrice;
}
