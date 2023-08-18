package com.inventory.inventoryservice.stock.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class StockSearchDto extends SearchDto {

    private Long id;
    private String product;
    private Long productId;
    private Double currentStock;
    private Boolean enabled;


}
