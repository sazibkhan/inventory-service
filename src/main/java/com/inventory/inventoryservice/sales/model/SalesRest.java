package com.inventory.inventoryservice.sales.model;

import com.inventory.inventoryservice.customer.model.CustomerEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesRest {

    private LocalDateTime salesDate;
    private CustomerEntity customer;
    private Long customerId;
    private String customerName;
    private Boolean enabled;
}
