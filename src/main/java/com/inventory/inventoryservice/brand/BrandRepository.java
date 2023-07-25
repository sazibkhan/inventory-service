package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long>,
                                         QuerydslPredicateExecutor<BrandEntity> {

}
