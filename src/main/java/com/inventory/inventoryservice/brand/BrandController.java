package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;

  @PostMapping
  @PreAuthorize("hasRole('BRAND_CREATE')")
  public ResponseEntity<BrandRest> saveBrand(@RequestBody BrandDto brandDto) {
    BrandRest brandRest = brandService.saveBrand(brandDto);
    return ResponseEntity.status(HttpStatus.OK).body(brandRest);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('BRAND_GET')")
  public ResponseEntity<BrandRest> getBrandById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(brandService.getBrandById(id));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('BRAND_UPDATE')")
  public ResponseEntity<BrandRest> updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDto) {
    BrandRest brandRest = brandService.updateBrand(id, brandDto);
    return ResponseEntity.status(HttpStatus.OK).body(brandRest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
    brandService.deleteBrand(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  public ResponseEntity<?> searchPage(@RequestBody BrandSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(brandService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  public ResponseEntity<?> searchList(@RequestBody BrandSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(brandService.searchList(searchDto));
  }

}
