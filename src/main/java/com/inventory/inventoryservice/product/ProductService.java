package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.category.CategoryValidatorService;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private  final ProductRepository productRepository;
    private final ProductValidatorService productValidatorService;
    private final BrandValidatorService brandValidatorService;
    private final CategoryValidatorService categoryValidatorService;
    public ProductRest saveProduct(ProductDto productDto) {

        ProductEntity product = ProductTransform.toProductEntity(productDto);
        var  brand = brandValidatorService.ifFoundByIdReturnElseThrow(productDto.getBrandId());
       // var  category = categoryValidatorService.ifFoundByIdReturnElseThrow(productDto.getCategoryId());
        product.setBrand(brand);
      //  product.setCategory(category);

        productRepository.save(product);
        return ProductTransform.toProductRest(product);
    }
}
