package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.category.CategoryValidatorService;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryService productQueryService;
    private final ProductValidatorService productValidatorService;

    public ProductRest saveProduct(ProductDto productDto) {

        ProductEntity product = productValidatorService.validateAndReturnProductSave(productDto);

        productRepository.save(product);
        return ProductTransform.toProductRest(product);
    }











  public Page<ProductRest> searchPage(ProductSearchDto searchDto) {

      Page<ProductEntity> page = productQueryService.searchPage(searchDto);

      List<ProductRest> productRestList = ProductTransform.toProductRestList(page.getContent());

      return new PageImpl<>(productRestList, page.getPageable(), page.getTotalElements());

  }


    public List<ProductRest> searchList(ProductSearchDto searchDto) {
        return ProductTransform.toProductRestList(productQueryService.searchList(searchDto));
    }


}
