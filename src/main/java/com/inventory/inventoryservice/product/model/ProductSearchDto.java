package com.inventory.inventoryservice.product.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class ProductSearchDto extends SearchDto {

  private Long id;
  private String productName;
  private String description;
  private String barCode;

}
