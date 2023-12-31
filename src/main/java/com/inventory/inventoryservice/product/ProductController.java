package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductRest> saveProduct(@RequestBody ProductDto productDto) {

            ProductRest productRest = productService.saveProduct(productDto);
            return ResponseEntity.status(HttpStatus.OK).body(productRest);
    }

    @PostMapping("/search-page")
    public ResponseEntity<?> searchPage(@RequestBody ProductSearchDto searchDto) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(productService.searchPage(searchDto));
    }

    @PostMapping("/search-list")
    public ResponseEntity<?> searchList(@RequestBody ProductSearchDto searchDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.searchList(searchDto));
    }




}
