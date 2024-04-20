package com.inventory.inventoryservice.security;


import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.purchase.PurchaseItemTransform;
import com.inventory.inventoryservice.purchase.PurchaseTransform;
import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import com.inventory.inventoryservice.security.model.UserDto;
import com.inventory.inventoryservice.security.model.UserEntity;
import com.inventory.inventoryservice.security.model.UserRoleDto;
import com.inventory.inventoryservice.security.model.UserRoleEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserValidatorService {

  private final UserRepository userRepository;


  public UserEntity ifFoundByIdReturnElseThrow(Long id) {
    Objects.requireNonNull(id);
    return userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException(String
        .format("User not found with id [%s]", id)));
  }

  public UserEntity ifFoundByUsernameReturnElseThrow(String username) {
    return userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException(String
        .format("User not found by username [%s]", username)));
  }




  public List<UserRoleEntity> validateAndReturnUserRoleList(UserDto userDto,
                                                            UserEntity user) {
    return userDto.getRoles().stream()
            .map(itm-> {
              UserRoleEntity userRoleItem = UserRoleTransform.toUserRoleEntity(itm);
              userRoleItem.setUser(user);
              userRoleItem.setRoleName(itm.getRoleName());
              return userRoleItem;
            }).collect(Collectors.toList());

  }


}
