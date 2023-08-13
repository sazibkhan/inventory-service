package com.inventory.inventoryservice.sales.model;

import com.inventory.inventoryservice.customer.model.CustomerEntity;
import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain=true)
public class SalesSearchDto extends SearchDto {

  private Long id;
  private LocalDateTime salesDate;
  private CustomerEntity customer;
  private Long customerId;
  private Boolean enabled;

}
