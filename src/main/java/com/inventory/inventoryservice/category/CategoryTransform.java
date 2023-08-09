package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.category.model.CategoryRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryTransform {
    public static CategoryEntity toCategoryEntity(CategoryDto categoryDto) {

        var category = new CategoryEntity();
        BeanUtils.copyProperties(categoryDto, category);
        return category;
    }

    public static CategoryRest toCategoryRest(CategoryEntity category) {
        var rest = new CategoryRest();
        BeanUtils.copyProperties(category, rest);
        return rest;
    }

    public static List<CategoryRest> toBrandRestList(List<CategoryEntity> list) {
        return list.parallelStream()
                .map(CategoryTransform::toCategoryRest)
                .collect(Collectors.toList());

    }
}
