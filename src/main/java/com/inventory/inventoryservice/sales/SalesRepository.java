package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity,Long>,
        QuerydslPredicateExecutor<SalesEntity> {
}
