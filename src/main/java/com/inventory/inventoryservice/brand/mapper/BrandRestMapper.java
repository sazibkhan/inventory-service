package com.inventory.inventoryservice.brand.mapper;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BrandRestMapper implements Function<BrandEntity, BrandRest>{
  @Override
  public BrandRest apply(BrandEntity brand){
    var rest = new BrandRest();
    BeanUtils.copyProperties(brand,rest);
    return rest;
  }
}
