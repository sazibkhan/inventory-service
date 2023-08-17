package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.product.ProductTransform;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.sales.model.SalesDto;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalesTransform {

    public static SalesEntity toSalesEntity(SalesDto salesDto) {
        var sales = new SalesEntity();
        BeanUtils.copyProperties(salesDto,sales);
        return sales;
    }

    public static SalesRest toSalesRest(SalesEntity sales) {
        var rest = new SalesRest();
        BeanUtils.copyProperties(sales, rest);

        Optional.ofNullable(sales.getCustomer()).ifPresent(customer-> {
            rest.setCustomerName(customer.getCustomerName());
        });

        return rest;
    }

    public static List<SalesRest> toSalesRestList(List<SalesEntity> list) {
        return list.stream()
                .map(SalesTransform::toSalesRest)
                .collect(Collectors.toList());

    }
}
