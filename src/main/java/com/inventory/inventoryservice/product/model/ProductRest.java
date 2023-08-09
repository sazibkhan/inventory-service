package com.inventory.inventoryservice.product.model;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRest {

    private String productName;
    private String discription;
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
