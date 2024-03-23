package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.sales.model.SalesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItemEntity, Long> {

    List<SalesItemEntity> findAllBySalesId(Long salesId);
}
