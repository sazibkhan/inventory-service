package com.inventory.inventoryservice.category.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRest {

    private Long id;
    private String categoryName;
    private Boolean enabled;
}
