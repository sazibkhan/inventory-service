package com.inventory.inventoryservice.stock.model;

import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

    private String product;
    private Long productId;
    private Double currentStock;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private  Long updatedBy;

}
