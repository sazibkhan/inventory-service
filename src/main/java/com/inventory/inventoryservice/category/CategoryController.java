package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryRest;
import com.inventory.inventoryservice.category.model.CategorySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/categorys")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  @PreAuthorize("hasAnyRole('CATEGORY_CREATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<CategoryRest> saveCategory(@RequestBody CategoryDto categoryDto) {
    CategoryRest categoryRest = categoryService.saveCategory(categoryDto);
    return ResponseEntity.status(HttpStatus.OK).body(categoryRest);
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('CATEGORY_GET','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<List<CategoryRest>> getAllCategory() {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAnyRole('CATEGORY_GET','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<CategoryRest> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('CATEGORY_UPDATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<CategoryRest> updateCategory(@PathVariable Long id,
                                                     @RequestBody CategoryDto categoryDto) {
    CategoryRest categoryRest = categoryService.updateCategory(id, categoryDto);
    return ResponseEntity.status(HttpStatus.OK).body(categoryRest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('CATEGORY_DELETE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('CATEGORY_SEARCH','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody CategorySearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(categoryService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('CATEGORY_SEARCH','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody CategorySearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(categoryService.searchList(searchDto));
  }

}
