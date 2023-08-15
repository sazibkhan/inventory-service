package com.inventory.inventoryservice.category.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategorySearchDto extends SearchDto {

    private String categoryName;
    private Boolean enabled;
}
