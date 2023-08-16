package com.inventory.inventoryservice.customer.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchDto extends SearchDto {

  private String customerName;
  private String phoneNumber;
  private String customerType;
  private String email;
  private String customerImage;
  private Boolean enabled;
}
