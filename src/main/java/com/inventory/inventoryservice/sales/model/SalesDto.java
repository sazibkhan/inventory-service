package com.inventory.inventoryservice.sales.model;

import com.inventory.inventoryservice.customer.model.CustomerEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto {

    private LocalDateTime salesDate;
    private CustomerEntity customer;
    private Long customerId;
    private Boolean enabled;


}
