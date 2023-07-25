package com.inventory.inventoryservice.customer;

import com.inventory.inventoryservice.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>,
                                            QuerydslPredicateExecutor<CustomerEntity> {


}
