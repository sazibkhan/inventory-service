package com.inventory.inventoryservice.supplier.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRest {
    private String supplierName;
    private String supplierAddress;
    private String phoneNumber;
    private Boolean enabled;
}
