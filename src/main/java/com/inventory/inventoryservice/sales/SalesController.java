package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.product.ProductService;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import com.inventory.inventoryservice.sales.model.SalesDto;
import com.inventory.inventoryservice.sales.model.SalesRest;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @PostMapping
    public ResponseEntity<SalesRest> saveSales(@RequestBody SalesDto salesDto) {

            SalesRest salesRest = salesService.saveSales(salesDto);
            return ResponseEntity.status(HttpStatus.OK).body(salesRest);
    }

    @PostMapping("/search-page")
    public ResponseEntity<?> searchPage(@RequestBody SalesSearchDto searchDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(salesService.searchPage(searchDto));
    }

}
