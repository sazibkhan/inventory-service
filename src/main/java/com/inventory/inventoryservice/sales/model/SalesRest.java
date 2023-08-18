package com.inventory.inventoryservice.sales.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesRest {

    private LocalDate salesDate;
    private Long customerId;
    private String customerName;
    private Boolean enabled;
}
