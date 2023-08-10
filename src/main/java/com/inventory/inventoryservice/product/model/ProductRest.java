package com.inventory.inventoryservice.product.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRest {

    private Long id;
    private String productName;
    private String description;
    private String barCode;
    private String brand;
    private Long brandId;
    private String category;
    private Long categoryId;
    private String productImages;
    private Double purchasePrice;
    private Double salesPrice;
    private Double discountAmount;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private  Long updatedBy;
}
