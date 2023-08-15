package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.category.model.CategoryRest;
import com.inventory.inventoryservice.category.model.CategorySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryValidatorService categoryValidatorService;
    private final CategoryQueryService categoryQueryService;

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



    public Page<CategoryRest>searchPage(CategorySearchDto searchDto){

        Page<CategoryEntity> page=categoryQueryService.searchPage(searchDto);
        List<CategoryRest> restList=CategoryTransform.toCategoryRestList(page.getContent());

        return  new PageImpl<>(restList,page.getPageable(),page.getTotalElements());
    }

    public List<CategoryRest> searchList(CategorySearchDto  searchDto) {
        return CategoryTransform.toCategoryRestList(categoryQueryService.searchList(searchDto));
    }




}
