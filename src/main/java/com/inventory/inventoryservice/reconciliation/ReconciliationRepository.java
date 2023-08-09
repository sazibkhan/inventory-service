package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.reconciliation.model.ReconciliationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReconciliationRepository extends JpaRepository< ReconciliationEntity, Long>,
        QuerydslPredicateExecutor<ReconciliationEntity> {
}
