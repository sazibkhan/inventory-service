package com.inventory.inventoryservice.sales.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain=true)
public class SalesSearchDto extends SearchDto {

  private Long id;
  private LocalDate salesDate;
  private Long customerId;
  private String customerName;
  private Long productId;
  private String productName;
  private Boolean enabled;

}
