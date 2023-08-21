package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Long>,
        QuerydslPredicateExecutor<PurchaseEntity> {

}
