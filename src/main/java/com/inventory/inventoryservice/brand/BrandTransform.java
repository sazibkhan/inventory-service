package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BrandTransform {

  public static BrandEntity toBrandEntity(BrandDto brandDto) {

    var brand = new BrandEntity();
    BeanUtils.copyProperties(brandDto, brand);
    return brand;
  }

  public static BrandRest toBrandRest(BrandEntity brand) {
    var rest = new BrandRest();
    BeanUtils.copyProperties(brand, rest);
    return rest;
  }

  public static List<BrandRest> toBrandRestList(List<BrandEntity> list) {
    return list.parallelStream()
        .map(BrandTransform::toBrandRest)
        .collect(Collectors.toList());

  }
}
