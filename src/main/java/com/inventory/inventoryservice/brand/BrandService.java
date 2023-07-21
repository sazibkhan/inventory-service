package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

  private final BrandRepository brandRepository;
  private final BrandValidatorService brandValidatorService;

  public BrandRest saveBrand(BrandDto brandDto) {

    BrandEntity brand = BrandTransform.toBrandEntity(brandDto);
    brandRepository.save(brand);

    return BrandTransform.toBrandRest(brand);
  }

  public BrandRest updateBrand(Long id, BrandDto brandDto) {

    BrandEntity brand = brandValidatorService.ifFoundByIdReturnElseThrow(id);
    brand.setBrandName(brandDto.getBrandName());
    brand.setEnabled(brandDto.getEnabled());
    brandRepository.save(brand);

    return BrandTransform.toBrandRest(brand);
  }

  public void deleteBrand(Long id) {

    BrandEntity brand = brandValidatorService.ifFoundByIdReturnElseThrow(id);
    brandRepository.deleteById(brand.getId());
  }
}
