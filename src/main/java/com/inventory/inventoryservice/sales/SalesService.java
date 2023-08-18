package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.product.ProductTransform;
import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import com.inventory.inventoryservice.sales.model.SalesDto;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesRest;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;
    private final SalesItemRepository salesItemRepository;
    private final SalesQueryService salesQueryService;
    private final SalesValidatorService salesValidatorService;
    private final ProductValidatorService productValidatorService;

    @Transactional
    public SalesRest saveSales(SalesDto salesDto) {

        SalesEntity sales = salesValidatorService.validateAndReturnSalesSave(salesDto);
        salesRepository.save(sales);

        salesDto.getItems().forEach(salesItemDto-> {
            SalesItemEntity salesItem = SalesItemTransform.toSalesItemEntity(salesItemDto);
            salesItem.setSales(sales);
            salesItem.setProduct(productValidatorService
                .ifFoundByIdReturnElseThrow(salesItemDto.getProductId()));
            salesItemRepository.save(salesItem);
        });

        return SalesTransform.toSalesRest(sales);
    }

    public Page<SalesRest> searchPage(SalesSearchDto searchDto) {

        Page<SalesEntity> page = salesQueryService.searchPage(searchDto);

        List<SalesRest> salesRestList = SalesTransform.toSalesRestList(page.getContent());

        return new PageImpl<>(salesRestList, page.getPageable(), page.getTotalElements());

    }

    public List<SalesRest> searchList(SalesSearchDto searchDto) {
        return SalesTransform.toSalesRestList(salesQueryService.searchList(searchDto));
    }


}
