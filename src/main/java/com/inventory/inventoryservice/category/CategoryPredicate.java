package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.category.model.CategorySearchDto;
import com.inventory.inventoryservice.category.model.QCategoryEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


public class CategoryPredicate {
    private final static QCategoryEntity qCATEGORY=QCategoryEntity.categoryEntity;

    public static Predicate search(CategorySearchDto searchDto){

        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.isNotEmpty(searchDto.getCategoryName())){
            builder.and(qCATEGORY.categoryName.eq(searchDto.getCategoryName()));
        }

        if(ObjectUtils.isNotEmpty(searchDto.getEnabled())) {
          builder.and(qCATEGORY.enabled.eq(searchDto.getEnabled()));
        }

        return builder;
    }



}
