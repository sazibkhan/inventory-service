package com.inventory.inventoryservice.sales;


import com.inventory.inventoryservice.customer.CustomerValidatorService;
import com.inventory.inventoryservice.product.ProductTransform;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.sales.model.SalesDto;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SalesValidatorService {

    private final SalesRepository salesRepository;
    private final CustomerValidatorService customerValidatorService;


    public SalesEntity ifFoundByIdReturnElseThrow(Long id) {
        Objects.requireNonNull(id);
        return salesRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Sales not found with id [%s]",id)));
    }

    public SalesEntity validateAndReturnSalesSave(SalesDto salesDto) {

        var entity = SalesTransform.toSalesEntity(salesDto);

        if(ObjectUtils.isNotEmpty(salesDto.getCustomerId())) {
            entity.setCustomer(customerValidatorService
                    .ifFoundByIdReturnElseThrow(salesDto.getCustomerId()));
        }
        return entity;
    }


}
