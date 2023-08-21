package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.reconciliation.model.ReconciliationItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReconciliationItemRepository extends JpaRepository<ReconciliationItemEntity,Long> {
}
