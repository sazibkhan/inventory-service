package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItemEntity,Long> {
}
