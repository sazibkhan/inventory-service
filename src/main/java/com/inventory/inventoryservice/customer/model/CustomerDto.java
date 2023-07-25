package com.inventory.inventoryservice.customer.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String customerName;

    private String phoneNumber;

    private String customerType;

    private String email;

    private String customerImage;

    private Boolean enabled;



}
