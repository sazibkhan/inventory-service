package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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


}
