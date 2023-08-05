package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity,Long>, QuerydslPredicateExecutor<SupplierEntity> {
}
