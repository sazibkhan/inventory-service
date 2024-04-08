package com.inventory.inventoryservice.stock.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockRest {

    private String product;
    private Long productId;
    private Double currentStock;
    private boolean enabled;

}
