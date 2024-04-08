package com.inventory.inventoryservice.category.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String categoryName;
    private Boolean enabled;

}
