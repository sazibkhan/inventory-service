package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.product.ProductService;
import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.stock.model.StockDto;
import com.inventory.inventoryservice.stock.model.StockEntity;
import com.inventory.inventoryservice.stock.model.StockRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final StockValidatorService stockValidatorService;
    private final ProductService productService ;
    private final ProductValidatorService productValidatorService;
    public StockRest saveStock(StockDto stockDto) {

        StockEntity stock = StockTransform.toStockEntity(stockDto);
        var product=productValidatorService.ifFoundByIdReturnElseThrow(stockDto.getProductId());
        stock.setProduct(product);
        stockRepository.save(stock);

        return StockTransform.toStockRest(stock);
    }

}

