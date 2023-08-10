package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BrandValidatorService {

  private final BrandRepository brandRepository;

  public BrandEntity ifFoundByIdReturnElseThrow(Long id) {
    Objects.requireNonNull(id);
    return brandRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException(String
            .format("Brand not found with id [%s]",id)));

  }
}
