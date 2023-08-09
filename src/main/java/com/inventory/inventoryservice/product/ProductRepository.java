package com.inventory.inventoryservice.product;


import com.inventory.inventoryservice.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>,
        QuerydslPredicateExecutor<ProductEntity> {
}
