package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.UserRoleDto;
import com.inventory.inventoryservice.security.model.UserRoleEntity;
import org.springframework.beans.BeanUtils;

public class UserRoleTransform {


    public static UserRoleEntity toUserRoleEntity(UserRoleDto userRoleDto) {
        var userRole = new UserRoleEntity();
        BeanUtils.copyProperties(userRoleDto, userRole);
        return userRole;
    }



}
