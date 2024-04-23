package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.category.CategoryValidatorService;
import com.inventory.inventoryservice.category.model.CategoryRest;
import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductRest;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService{

  private final ProductRepository productRepository;
  private final ProductQueryService productQueryService;
  private final ProductValidatorService productValidatorService;
  private final BrandValidatorService brandValidatorService;
  private final CategoryValidatorService categoryValidatorService;


  public ProductRest saveProduct(ProductDto productDto){
    ProductEntity product = ProductTransform.toProductEntity(productDto);
    if(ObjectUtils.isNotEmpty(productDto.getBrandId())){
      product.setBrand(brandValidatorService.ifFoundByIdReturnElseThrow(productDto.getBrandId()));
    }
    if(ObjectUtils.isNotEmpty(productDto.getCategoryId())){
      product.setCategory(categoryValidatorService.ifFoundByIdReturnElseThrow(productDto.getCategoryId()));
    }
    productRepository.save(product);
    return ProductTransform.toProductRest(product);
  }

  public ProductRest updateProduct(Long id, ProductDto productDTO){
    var productEntity = productValidatorService.ifFoundByIdReturnElseThrow(id);
    var brandEntity = brandValidatorService.ifFoundByIdReturnElseThrow(productDTO.getBrandId());
    var categoryEntity = categoryValidatorService.ifFoundByIdReturnElseThrow(productDTO.getCategoryId());
    productEntity.setProductName(productDTO.getProductName());
    productEntity.setPurchasePrice(productDTO.getPurchasePrice());
    productEntity.setSalesPrice(productDTO.getSalesPrice());
    productEntity.setBrand(brandEntity);
    productEntity.setCategory(categoryEntity);
    productRepository.save(productEntity);
    return ProductTransform.toProductRest(productEntity);
  }

  public void deleteProduct(Long id){
    var productEntity = productValidatorService.ifFoundByIdReturnElseThrow(id);
    productRepository.deleteById(productEntity.getId());
  }


  public Page<ProductRest> searchPage(ProductSearchDto searchDto){
    Page<ProductEntity> page = productQueryService.searchPage(searchDto);
    List<ProductRest> productRestList = ProductTransform.toProductRestList(page.getContent());
    return new PageImpl<>(productRestList, page.getPageable(), page.getTotalElements());
  }

  public List<ProductRest> searchList(ProductSearchDto searchDto){
    return ProductTransform.toProductRestList(productQueryService.searchList(searchDto));
  }


}
