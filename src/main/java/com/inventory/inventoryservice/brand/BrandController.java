package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.category.model.CategoryRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;

  @PostMapping
  @PreAuthorize("hasAnyRole('BRAND_CREATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<BrandRest> saveBrand(@RequestBody BrandDto brandDto) {
    BrandRest brandRest = brandService.saveBrand(brandDto);
    return ResponseEntity.status(HttpStatus.OK).body(brandRest);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('BRAND_GET','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<List<BrandRest>> getAllBrand() {
    return ResponseEntity.status(HttpStatus.OK).body(brandService.getAllBrand());
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('BRAND_GET','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<BrandRest> getBrandById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(brandService.getBrandById(id));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('BRAND_UPDATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<BrandRest> updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDto) {
    BrandRest brandRest = brandService.updateBrand(id, brandDto);
    return ResponseEntity.status(HttpStatus.OK).body(brandRest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('BRAND_DELETE','ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
  public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
    brandService.deleteBrand(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('BRAND_SEARCH','ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody BrandSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(brandService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('BRAND_SEARCH','ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody BrandSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(brandService.searchList(searchDto));
  }

}
