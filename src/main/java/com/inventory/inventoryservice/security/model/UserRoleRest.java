package com.inventory.inventoryservice.security.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleRest{
  private Long id;
  private Long userId;
  private String roleName;
}
