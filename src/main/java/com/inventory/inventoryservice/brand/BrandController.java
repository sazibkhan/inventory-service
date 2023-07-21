package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

  private final BrandService brandService;

  @PostMapping
  public ResponseEntity<BrandRest> saveBrand(@RequestBody BrandDto brandDto) {
    try {
      BrandRest brandRest = brandService.saveBrand(brandDto);
      return ResponseEntity.status(HttpStatus.OK).body(brandRest);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error!!");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<BrandRest> updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDto) {
    try {
      BrandRest brandRest = brandService.updateBrand(id, brandDto);
      return ResponseEntity.status(HttpStatus.OK).body(brandRest);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error!!");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
    try {
      brandService.deleteBrand(id);
      return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error!!");
    }
  }

}
