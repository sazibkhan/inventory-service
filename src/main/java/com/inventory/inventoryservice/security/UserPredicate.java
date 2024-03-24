package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.category.model.CategorySearchDto;
import com.inventory.inventoryservice.security.model.QUserEntity;
import com.inventory.inventoryservice.security.model.UserSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class UserPredicate {

    private final static QUserEntity qUser=QUserEntity.userEntity;

    public static Predicate search(UserSearchDto searchDto){
        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.isNotEmpty(searchDto.getFullName())){
            builder.and(qUser.fullName.eq(searchDto.getFullName()));
        }
        if(StringUtils.isNotEmpty(searchDto.getUsername())){
            builder.and(qUser.username.eq(searchDto.getUsername()));
        }

        if(ObjectUtils.isNotEmpty(searchDto.getEnabled())) {
            builder.and(qUser.enabled.eq(searchDto.getEnabled()));
        }

        return builder;
    }




}
