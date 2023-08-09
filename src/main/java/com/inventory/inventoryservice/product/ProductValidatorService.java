package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductValidatorService {

    private final ProductRepository productRepository;

    public ProductEntity ifFoundByIdReturnElseThrow(Long id) {
        Objects.requireNonNull(id);

        return productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Product not found with id [%s]",id)));

    }
}
