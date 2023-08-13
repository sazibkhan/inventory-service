package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.product.ProductQueryService;
import com.inventory.inventoryservice.product.ProductRepository;
import com.inventory.inventoryservice.product.ProductTransform;
import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
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


import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;
    private final SalesQueryService salesQueryService;
    private final SalesValidatorService salesValidatorService;

    public SalesRest saveSales(SalesDto salesDto) {

        SalesEntity sales = salesValidatorService.validateAndReturnCustomerSave(salesDto);
        salesRepository.save(sales);
        return SalesTransform.toSalesRest(sales);
    }
    public Page<SalesRest> searchPage(SalesSearchDto searchDto) {

        Page<SalesEntity> page = salesQueryService.searchPage(searchDto);

        List<SalesRest> salesRestList = SalesTransform.toSalesRestList(page.getContent());

        return new PageImpl<>(salesRestList, page.getPageable(), page.getTotalElements());

    }


}
