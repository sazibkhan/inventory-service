package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.category.CategoryTransform;
import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.category.model.CategoryRest;
import com.inventory.inventoryservice.security.model.UserDto;
import com.inventory.inventoryservice.security.model.UserEntity;
import com.inventory.inventoryservice.security.model.UserRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserTransform {

    public static UserEntity toUserEntity(UserDto userDto) {

        var user=new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
    public static UserRest toUserRest(UserEntity user){
        var userRest=new UserRest();
        BeanUtils.copyProperties(user,userRest);
        return userRest;
    }
    public static List<UserRest> toUserRestList(List<UserEntity> list) {
        return list.parallelStream()
                .map(UserTransform::toUserRest)
                .collect(Collectors.toList());

    }
}
