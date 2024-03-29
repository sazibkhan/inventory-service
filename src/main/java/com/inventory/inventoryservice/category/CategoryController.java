package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryRest;
import com.inventory.inventoryservice.category.model.CategorySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/categorys")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryRest> saveCategory(@RequestBody CategoryDto categoryDto) {
    CategoryRest categoryRest = categoryService.saveCategory(categoryDto);
    return ResponseEntity.status(HttpStatus.OK).body(categoryRest);
  }

  @GetMapping
  public ResponseEntity<List<CategoryRest>> getAllCategory() {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryRest> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryRest> updateCategory(@PathVariable Long id,
                                                     @RequestBody CategoryDto categoryDto) {
    CategoryRest categoryRest = categoryService.updateCategory(id, categoryDto);
    return ResponseEntity.status(HttpStatus.OK).body(categoryRest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  public ResponseEntity<?> searchPage(@RequestBody CategorySearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(categoryService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  public ResponseEntity<?> searchList(@RequestBody CategorySearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(categoryService.searchList(searchDto));
  }

}
