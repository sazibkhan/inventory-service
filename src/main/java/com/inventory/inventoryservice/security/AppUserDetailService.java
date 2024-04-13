package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.CustomPrincipal;
import com.inventory.inventoryservice.security.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService{

  private final UserValidatorService userValidatorService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    UserEntity user = userValidatorService.ifFoundByUsernameReturnElseThrow(username);
    //todo: custom exception
    return new CustomPrincipal(user);
  }
}
