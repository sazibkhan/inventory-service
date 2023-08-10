package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.product.model.ProductSearchDto;
import com.inventory.inventoryservice.product.model.QProductEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class ProductPredicate {

  private final static QProductEntity qProduct = QProductEntity.productEntity;

  public static Predicate search(ProductSearchDto searchDto) {

    BooleanBuilder builder = new BooleanBuilder();

    if(ObjectUtils.isNotEmpty(searchDto.getId())) {
      builder.and(qProduct.id.eq(searchDto.getId()));
    }
    if(StringUtils.isNotEmpty(searchDto.getProductName())) {
      builder.and(qProduct.productName.eq(searchDto.getProductName()));
    }
    if(StringUtils.isNotEmpty(searchDto.getDescription())) {
      builder.and(qProduct.description.eq(searchDto.getDescription()));
    }
    if(StringUtils.isNotEmpty(searchDto.getBarCode())) {
      builder.and(qProduct.barCode.eq(searchDto.getBarCode()));
    }
    return builder;
  }
}
