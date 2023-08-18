package com.inventory.inventoryservice.sales.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {

    private LocalDate salesDate;
    private Long customerId;
    private Boolean enabled;
    private List<SalesItemDto> items;


}
