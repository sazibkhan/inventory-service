package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>,
  QuerydslPredicateExecutor<UserEntity> {

}
