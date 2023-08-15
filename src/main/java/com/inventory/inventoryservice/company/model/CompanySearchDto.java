package com.inventory.inventoryservice.company.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanySearchDto extends SearchDto {

    private String companyName;
    private String email;
    private String mobile;
    private String address;
    private Boolean enabled;


}
