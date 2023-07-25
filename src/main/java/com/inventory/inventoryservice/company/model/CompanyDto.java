package com.inventory.inventoryservice.company.model;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private String companyName;
    private String email;
    private String mobile;
    private String address;
    private Boolean enabled;

}
