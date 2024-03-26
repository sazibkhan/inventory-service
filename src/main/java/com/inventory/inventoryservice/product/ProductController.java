package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.category.model.CategoryRest;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @PutMapping("/{id}")
    public ResponseEntity<ProductRest> updateProduct(@PathVariable Long id,
                                                     @RequestBody @Valid ProductDto productDto) {
        ProductRest productRest = productService.updateProduct(id,productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productRest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
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
