package com.inventory.inventoryservice.brand.mapper;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BrandEntityMapper implements Function<BrandDto, BrandEntity> {
  @Override
  public BrandEntity apply(BrandDto brandDto){
    var entity = new BrandEntity();
    BeanUtils.copyProperties(brandDto,entity);
    return entity;
  }
}
