package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.sales.model.QSalesEntity;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class SalesPredicate {

    private final static QSalesEntity qSales = QSalesEntity.salesEntity;

    public static Predicate search(SalesSearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if(ObjectUtils.isNotEmpty(searchDto.getId())) {
            builder.and(qSales.id.eq(searchDto.getId()));
        }
        if(StringUtils.isNotEmpty(searchDto.getCustomer().getCustomerName())) {
            builder.and(qSales.customer.eq(searchDto.getCustomer()));
        }
        if(StringUtils.isNotEmpty(searchDto.getCustomer().getCustomerType())) {
            builder.and(qSales.customer.eq(searchDto.getCustomer()));
        }
        if(StringUtils.isNotEmpty(searchDto.getCustomer().getCustomerType())) {
            builder.and(qSales.customer.eq(searchDto.getCustomer()));
        }

        return builder;
    }
}
