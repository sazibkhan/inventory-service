package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.stock.model.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long>,
        QuerydslPredicateExecutor<StockEntity> {
}
