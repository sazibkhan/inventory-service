package com.inventory.inventoryservice.product;

import com.inventory.inventoryservice.brand.model.QBrandEntity;
import com.inventory.inventoryservice.category.model.QCategoryEntity;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import com.inventory.inventoryservice.product.model.QProductEntity;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

  private final EntityManager entityManager;

  public Page<ProductEntity> searchPage(ProductSearchDto searchDto) {

    QProductEntity qProductEntity = QProductEntity.productEntity;
    QBrandEntity qBrandEntity = QBrandEntity.brandEntity;
    QCategoryEntity qCategoryEntity = QCategoryEntity.categoryEntity;

    Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

    JPAQuery<ProductEntity> query = new JPAQuery<>(entityManager);

    List<ProductEntity> productList = query.from(qProductEntity)
//        .leftJoin(qProductEntity.brand, qBrandEntity).fetchJoin()
//        .leftJoin(qProductEntity.category, qCategoryEntity).fetchJoin()
        .where(ProductPredicate.search(searchDto))
        .orderBy(qProductEntity.productName.asc())
        .limit(pageable.getPageSize()).offset(pageable.getOffset())
        .fetch();

    return new PageImpl<>(productList, pageable, query.fetchCount());
  }
}
