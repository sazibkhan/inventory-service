package com.inventory.inventoryservice.supplier.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "suppliers")
public class SupplierEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "supplier_name")
  private String supplierName;

  @Column(name = "supplier_address")
  private String supplierAddress;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "supplier_type")
  @Enumerated(EnumType.STRING)
  private SupplierTypeEnum supplierType;

  @Column(name = "enabled")
  private Boolean enabled;

}
