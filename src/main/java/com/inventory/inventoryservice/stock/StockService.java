package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.product.ProductService;
import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.sales.SalesTransform;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesRest;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import com.inventory.inventoryservice.stock.model.StockDto;
import com.inventory.inventoryservice.stock.model.StockEntity;
import com.inventory.inventoryservice.stock.model.StockRest;
import com.inventory.inventoryservice.stock.model.StockSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockQueryService stockQueryService;
    private final StockValidatorService stockValidatorService;
    private final ProductValidatorService productValidatorService;

    public StockRest saveStock(StockDto stockDto) {

        StockEntity stock = StockTransform.toStockEntity(stockDto);
        var product=productValidatorService.ifFoundByIdReturnElseThrow(stockDto.getProductId());
        stock.setProduct(product);
        stockRepository.save(stock);

        return StockTransform.toStockRest(stock);
    }


    public Page<StockRest> searchPage(StockSearchDto searchDto) {

        Page<StockEntity> page = stockQueryService.searchPage(searchDto);

        List<StockRest> stockRestList = StockTransform.toStockRestList(page.getContent());

        return new PageImpl<>(stockRestList, page.getPageable(), page.getTotalElements());

    }

    public List<StockRest> searchList(StockSearchDto searchDto) {
        return StockTransform.toStockRestList(stockQueryService.searchList(searchDto));
    }


}

