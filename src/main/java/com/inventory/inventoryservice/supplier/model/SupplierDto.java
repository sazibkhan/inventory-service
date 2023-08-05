package com.inventory.inventoryservice.supplier.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {

    private String supplierName;
    private String supplierAddress;
    private String phoneNumber;
    private boolean enabled;
}
