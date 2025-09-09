package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.exception.UserNotFoundException;
import com.inventory.inventoryservice.security.model.CustomPrincipal;
import com.inventory.inventoryservice.security.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

  private final UserValidatorService userValidatorService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      UserEntity user = userValidatorService.ifFoundByUsernameReturnElseThrow(username);
      return new CustomPrincipal(user);

    } catch (UserNotFoundException e) {
      // Spring Security কে UsernameNotFoundException throw করতে হবে
      throw new UsernameNotFoundException(e.getMessage());
    }
  }
}