package com.inventory.inventoryservice.reconciliation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReconciliationApproveRequest {
  private ReconciliationStatusEnum reconciliationStatus;
}
