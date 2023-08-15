package com.inventory.inventoryservice.category;

import com.inventory.inventoryservice.brand.BrandPredicate;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.category.model.CategorySearchDto;
import com.inventory.inventoryservice.utils.IterableUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryQueryService {

    private final CategoryRepository categoryRepository;

    public Page<CategoryEntity> searchPage(CategorySearchDto searchDto) {

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        Predicate predicate = CategoryPredicate.search(searchDto);

        return categoryRepository.findAll(predicate, pageable);
    }

    public List<CategoryEntity> searchList(CategorySearchDto searchDto) {

        Predicate predicate = CategoryPredicate.search(searchDto);
        return IterableUtils.toList(categoryRepository.findAll(predicate));
    }

}
