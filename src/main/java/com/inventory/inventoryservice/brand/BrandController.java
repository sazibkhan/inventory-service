package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;

  @PostMapping
  public ResponseEntity<BrandRest> saveBrand(@RequestBody BrandDto brandDto) {

    BrandRest brandRest = brandService.saveBrand(brandDto);
    return ResponseEntity.status(HttpStatus.OK).body(brandRest);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BrandRest> updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDto) {

    BrandRest brandRest = brandService.updateBrand(id, brandDto);
    return ResponseEntity.status(HttpStatus.OK).body(brandRest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBrand(@PathVariable Long id) {

    brandService.deleteBrand(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @GetMapping
  public ResponseEntity<?> params(@RequestParam Map<String, String> params) {
    params.forEach((key, value) -> System.out.printf("key: %s, value: %s%n", key, value));

    return ResponseEntity.ok("");
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
