package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{

    Optional<UserRoleEntity> findByRoleName(String roleName);


}
