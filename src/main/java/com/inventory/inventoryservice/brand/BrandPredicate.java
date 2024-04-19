package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.brand.model.QBrandEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class BrandPredicate {

  private final static QBrandEntity qBrand = QBrandEntity.brandEntity;

  public static Predicate search(BrandSearchDto searchDto) {
    BooleanBuilder builder = new BooleanBuilder();
    if(StringUtils.isNotEmpty(searchDto.getBrandName())) {
      builder.and(qBrand.brandName.eq(searchDto.getBrandName()));
    }
    if(ObjectUtils.isNotEmpty(searchDto.getEnabled())) {
      builder.and(qBrand.enabled.eq(searchDto.getEnabled()));
    }
    return builder;
  }
}
