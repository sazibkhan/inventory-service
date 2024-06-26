package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.category.model.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryValidatorService{

  private final CategoryRepository categoryRepository;

  public CategoryEntity ifFoundByIdReturnElseThrow(Long id){
    Objects.requireNonNull(id);
    return categoryRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException(String
        .format("Category not found with id [%s]", id)));

  }
}
