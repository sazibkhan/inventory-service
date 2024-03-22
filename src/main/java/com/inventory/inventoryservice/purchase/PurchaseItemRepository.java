package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItemEntity,Long> {

  List<PurchaseItemEntity> findAllByPurchaseId(Long purchaseId);
}
