package com.inventory.inventoryservice.customer.model;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRest {


    private String customerName;
    private String phoneNumber;
    private String customerType;
    private String email;
    private String customerImage;
    private Boolean enabled;




}
