package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.brand.BrandService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/categorys")
@RequiredArgsConstructor
public class CategoryController {

    private final  CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryRest> saveCategory(@RequestBody CategoryDto categoryDto) {
        try {
            CategoryRest categoryRest = categoryService.saveCategory(categoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(categoryRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRest> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        try {
            CategoryRest categoryRest  = categoryService.updateCategory(id, categoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(categoryRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }
}
