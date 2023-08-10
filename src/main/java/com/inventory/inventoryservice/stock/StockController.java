package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.BrandService;
import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.stock.model.StockDto;
import com.inventory.inventoryservice.stock.model.StockEntity;
import com.inventory.inventoryservice.stock.model.StockRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockRest> saveStock(@RequestBody StockDto stockDto) {
        try {
            StockRest stockRest = stockService.saveStock(stockDto);
            return ResponseEntity.status(HttpStatus.OK).body(stockRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }
}
