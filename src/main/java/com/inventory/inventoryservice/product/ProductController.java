package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  @PreAuthorize("hasAnyRole('PRODUCT_CREATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<ProductRest> saveProduct(@RequestBody ProductDto productDto) {
    ProductRest productRest = productService.saveProduct(productDto);
    return ResponseEntity.status(HttpStatus.OK).body(productRest);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('PRODUCT_UPDATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<ProductRest> updateProduct(@PathVariable Long id,
                                                   @RequestBody @Valid ProductDto productDto) {
    ProductRest productRest = productService.updateProduct(id, productDto);
    return ResponseEntity.status(HttpStatus.OK).body(productRest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('PRODUCT_DELETE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('PRODUCT_SEARCH','ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody ProductSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('PRODUCT_SEARCH','ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody ProductSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(productService.searchList(searchDto));
  }


}
