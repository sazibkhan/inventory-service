package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.category.CategoryValidatorService;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductValidatorService {

    private final ProductRepository productRepository;
    private final BrandValidatorService brandValidatorService;
    private final CategoryValidatorService categoryValidatorService;

    public ProductEntity ifFoundByIdReturnElseThrow(Long id) {
        Objects.requireNonNull(id);
        return productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Product not found with id [%s]",id)));

    }


    public ProductEntity validateAndReturnProductSave(ProductDto productDto) {
        var entity = ProductTransform.toProductEntity(productDto);
        if(ObjectUtils.isNotEmpty(productDto.getBrandId())) {
            entity.setBrand(brandValidatorService
                .ifFoundByIdReturnElseThrow(productDto.getBrandId()));
        }
        if(ObjectUtils.isNotEmpty(productDto.getCategoryId())) {
            entity.setCategory(categoryValidatorService
                .ifFoundByIdReturnElseThrow(productDto.getCategoryId()));
        }
        return entity;
    }
}
