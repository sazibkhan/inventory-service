package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.product.model.ProductDto;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductTransform{

  public static ProductEntity toProductEntity(ProductDto productDto){
    var product = new ProductEntity();
    BeanUtils.copyProperties(productDto, product);
    return product;
  }

  public static ProductRest toProductRest(ProductEntity product){
    var rest = new ProductRest();
    BeanUtils.copyProperties(product, rest);

    Optional.ofNullable(product.getBrand()).ifPresent(brand -> {
      rest.setBrandName(brand.getBrandName());
    });

    Optional.ofNullable(product.getCategory()).ifPresent(category -> {
      rest.setCategoryName(category.getCategoryName());
    });
    return rest;
  }

  public static List<ProductRest> toProductRestList(List<ProductEntity> list){
    return list.stream()
      .map(ProductTransform::toProductRest)
      .collect(Collectors.toList());

  }
}
