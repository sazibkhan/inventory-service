package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.*;
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
    return userRest;
  }

  public static List<UserRest> toUserRestList(List<UserEntity> list) {
    return list.parallelStream()
      .map(UserTransform::toUserRest)
      .collect(Collectors.toList());

  }



}
