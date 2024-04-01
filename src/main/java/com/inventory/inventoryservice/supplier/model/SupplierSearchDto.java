package com.inventory.inventoryservice.supplier.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplierSearchDto extends SearchDto {

    private String supplierName;
    private String supplierAddress;
    private String phoneNumber;
    private Boolean enabled;


}
