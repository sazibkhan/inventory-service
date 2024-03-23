package com.inventory.inventoryservice.purchase.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PurchaseItemDto {

  @NotNull
  private Long productId;
  @NotNull
  private Double quantity;
  @NotNull
  private Double purchasePrice;
  @NotNull
  private Double salesPrice;


}
