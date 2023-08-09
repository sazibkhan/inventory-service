package com.inventory.inventoryservice.product.model;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRest {

    private String productName;
    private String discription;
    private String barCode;
    private BrandEntity brand;
    private Long brandId;
    private CategoryEntity category;
    private Long categoryId;
    private String productImages;
    private Double purchasePrice;
    private Double salesPrice;
    private Double discountAmount;
    private boolean enabled;
}
