package com.inventory.inventoryservice.brand.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandRest {
  private  Long id;
  private String brandName;
  private Boolean enabled;
}
