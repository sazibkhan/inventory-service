package com.inventory.inventoryservice.security.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class UserDto {

    private String fullName;
    private String username;
    private String password;
    private String authority;
    private List<UserRoleDto> roles;
    private Boolean enabled;
}
