package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserTransform {

  public static UserEntity toUserEntity(UserDto userDto) {
    var user = new UserEntity();
    BeanUtils.copyProperties(userDto, user);
    return user;
  }

  public static UserRest toUserRest(UserEntity user) {
    var userRest = new UserRest();
    BeanUtils.copyProperties(user, userRest);
    if(CollectionUtils.isNotEmpty(user.getRoles())) {
      userRest.setRoles(user.getRoles().stream()
        .map(itm-> UserRoleRest.builder()
          .id(itm.getId())
          .userId(user.getId())
          .roleName(itm.getRoleName())
        .build()).collect(Collectors.toList()));
    }
    return userRest;
  }

  public static List<UserRest> toUserRestList(List<UserEntity> list) {
    return list.parallelStream()
      .map(UserTransform::toUserRest)
      .collect(Collectors.toList());

  }



}
