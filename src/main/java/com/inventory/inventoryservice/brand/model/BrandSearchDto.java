package com.inventory.inventoryservice.brand.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandSearchDto extends SearchDto {

  private String brandName;
  private Boolean enabled;
}
