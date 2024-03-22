package com.inventory.inventoryservice.stock.model;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

    private Long productId;
    private Double currentStock;

}
