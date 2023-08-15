package com.inventory.inventoryservice.company;

import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.brand.model.QBrandEntity;
import com.inventory.inventoryservice.category.model.QCategoryEntity;
import com.inventory.inventoryservice.company.model.CompanySearchDto;
import com.inventory.inventoryservice.company.model.QCompanyEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class CompanyPredicate {

  private final static QCompanyEntity qCOMPANY = QCompanyEntity.companyEntity;

  public static Predicate search(CompanySearchDto searchDto) {

    BooleanBuilder builder = new BooleanBuilder();

    if(StringUtils.isNotEmpty(searchDto.getCompanyName())) {
      builder.and(qCOMPANY.companyName.eq(searchDto.getCompanyName()));
    }
    if(ObjectUtils.isNotEmpty(searchDto.getEnabled())) {
      builder.and(qCOMPANY.enabled.eq(searchDto.getEnabled()));
    }

    return builder;
  }
}
