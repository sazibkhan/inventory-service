package com.inventory.inventoryservice.reconciliation.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class ReconciliationItemDto {

    @NotNull
    private Long productId;
    @NotNull
    private Double quantity;

}
