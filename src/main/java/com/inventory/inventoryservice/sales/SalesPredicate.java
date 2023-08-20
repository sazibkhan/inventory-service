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

        if(ObjectUtils.isNotEmpty(searchDto.getSalesDate())) {
            builder.and(qSales.salesDate.eq(searchDto.getSalesDate()));
        }

        if(ObjectUtils.isNotEmpty(searchDto.getCustomerId())) {
            builder.and(qSales.customer.id.eq(searchDto.getCustomerId()));
        }
        if(StringUtils.isNotEmpty(searchDto.getCustomerName())) {
            builder.and(qSales.customer.customerName.eq(searchDto.getCustomerName()));
        }


        return builder;
    }
}
