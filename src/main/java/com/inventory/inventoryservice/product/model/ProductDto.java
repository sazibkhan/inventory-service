package com.inventory.inventoryservice.product.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productName;
    private String description;
    private String barCode;

    private Long brandId;
    private String brandName;
    private Long categoryId;
    private String categoryName;

    private String productImages;
    private Double purchasePrice;
    private Double salesPrice;
    private Double discountAmount;

    private Boolean enabled;
}
