package com.inventory.inventoryservice.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItemEntity, Long> {

}
