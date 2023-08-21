package com.inventory.inventoryservice.purchase.model;

import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {


    private LocalDate purchaseDate;
    private String supplier;
    private Long supplierId;
    private Boolean enabled;
    private List<PurchaseItemDto> items;

}
