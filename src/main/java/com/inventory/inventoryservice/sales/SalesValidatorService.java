package com.inventory.inventoryservice.sales;


import com.inventory.inventoryservice.customer.CustomerValidatorService;
import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.sales.model.SalesDto;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesItemEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesValidatorService {

    private final SalesRepository salesRepository;
    private final CustomerValidatorService customerValidatorService;
    private final ProductValidatorService productValidatorService;

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

    public List<SalesItemEntity> validateAndReturnSalesItemList(SalesDto salesDto , SalesEntity sales) {
        return salesDto.getItems().stream()
                .map(itm-> {
                    SalesItemEntity salesItem = SalesItemTransform.toSalesItemEntity(itm);
                    salesItem.setSales(sales);
                    salesItem.setProduct(productValidatorService
                            .ifFoundByIdReturnElseThrow(itm.getProductId()));
                    salesItem.setQuantity(itm.getQuantity().doubleValue());
                    return salesItem;
                }).collect(Collectors.toList());
    }


}
