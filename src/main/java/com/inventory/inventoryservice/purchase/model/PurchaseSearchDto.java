package com.inventory.inventoryservice.purchase.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain=true)
public class PurchaseSearchDto extends SearchDto {

    private Long id;
    private LocalDate purchaseDate;
    private String supplier;
    private Long supplierId;
    private Boolean enabled;

}
