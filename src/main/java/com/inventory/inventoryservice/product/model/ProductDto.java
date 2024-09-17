package com.inventory.inventoryservice.product.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long brandId;
    private String brandName;
    private Long categoryId;
    private String categoryName;

    private String productName;
    private String barCode;
    private Double purchasePrice;
    private Double salesPrice;
    private Double discountAmount;
    private String description;
    private String productImages;
    private Boolean enabled;
}
