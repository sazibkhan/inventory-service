package com.inventory.inventoryservice.customer;

import com.inventory.inventoryservice.company.model.CompanySearchDto;
import com.inventory.inventoryservice.company.model.QCompanyEntity;
import com.inventory.inventoryservice.customer.model.CustomerSearchDto;
import com.inventory.inventoryservice.customer.model.QCustomerEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
public class CustomerPredicate {
    private final static QCustomerEntity qCUSTOMER = QCustomerEntity.customerEntity;

    public static Predicate search(CustomerSearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.isNotEmpty(searchDto.getCustomerName())) {
            builder.and(qCUSTOMER.customerName.eq(searchDto.getCustomerName()));
        }

        if(ObjectUtils.isNotEmpty(searchDto.getEnabled())) {
            builder.and(qCUSTOMER.enabled.eq(searchDto.getEnabled()));
        }

        return builder;
    }



}
