package com.inventory.inventoryservice.company;

import com.inventory.inventoryservice.company.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>,
        QuerydslPredicateExecutor<CompanyEntity> {


}
