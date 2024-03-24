package com.inventory.inventoryservice.security.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRest {

    private String fullName;
    private String username;
    private String password;
    private String authority;
    private List<UserRoleEntity> roles = new ArrayList<>();
    private Boolean enabled;


}
