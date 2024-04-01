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

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "updated_by")
  private Long updatedBy;


}
