package com.inventory.inventoryservice.config;

import com.inventory.inventoryservice.security.model.CustomPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long>{

  @Override
  public Optional<Long> getCurrentAuditor(){
    if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      throw new IllegalArgumentException("Invalid authentication");
    }

    return Optional.of(((CustomPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
  }
}
