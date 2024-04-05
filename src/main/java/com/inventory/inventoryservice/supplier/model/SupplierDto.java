package com.inventory.inventoryservice.supplier.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {

    private String supplierName;
    private SupplierTypeEnum supplierType;
    private String supplierAddress;
    private String phoneNumber;
    private Boolean enabled;
}
