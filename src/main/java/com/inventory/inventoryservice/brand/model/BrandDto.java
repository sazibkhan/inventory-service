package com.inventory.inventoryservice.brand.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

  private String brandName;
  private Boolean enabled;
}
