package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.category.model.CategoryRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryValidatorService categoryValidatorService;

    public CategoryRest saveCategory(CategoryDto categoryDto) {

        CategoryEntity category = CategoryTransform.toCategoryEntity(categoryDto);
        categoryRepository.save(category);
        return CategoryTransform.toCategoryRest(category);
    }

    public CategoryRest getCategoryById(Long id){
        CategoryEntity category = categoryValidatorService.ifFoundByIdReturnElseThrow(id);
        var response =new CategoryRest();
        BeanUtils.copyProperties(category,response);
        return response;
    }

    public List<CategoryRest> getAllCategory(){
        return categoryRepository.findAll().stream()
                .map(itm->{
                    var res =new CategoryRest();
                    BeanUtils.copyProperties(itm, res);
                    return res;
                }).collect(Collectors.toList());
    }
    public CategoryRest updateCategory(Long id, CategoryDto categoryDto) {

        CategoryEntity category = categoryValidatorService.ifFoundByIdReturnElseThrow(id);
        category.setCategoryName(categoryDto.getCategoryName());

        categoryRepository.save(category);
        return CategoryTransform.toCategoryRest(category);
    }

    public void deleteCategory(Long id) {

        CategoryEntity category = categoryValidatorService.ifFoundByIdReturnElseThrow(id);
        categoryRepository.deleteById(category.getId());
    }


}
