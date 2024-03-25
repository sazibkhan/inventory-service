package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.reconciliation.model.ReconciliationItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReconciliationItemRepository extends JpaRepository<ReconciliationItemEntity,Long> {

    List<ReconciliationItemEntity> findAllByReconciliationId(Long reconciliationId);
}
