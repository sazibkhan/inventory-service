package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.brand.model.QBrandEntity;
import com.inventory.inventoryservice.category.model.QCategoryEntity;
import com.inventory.inventoryservice.customer.model.QCustomerEntity;
import com.inventory.inventoryservice.product.ProductPredicate;
import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.product.model.ProductSearchDto;
import com.inventory.inventoryservice.product.model.QProductEntity;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
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
public class SalesQueryService {

    private final EntityManager entityManager;

    public Page<SalesEntity> searchPage(SalesSearchDto searchDto) {

        QSalesEntity qSalesEntity = QSalesEntity.salesEntity;
        QCustomerEntity qCustomerEntity = QCustomerEntity.customerEntity;


        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        JPAQuery<SalesEntity> query = new JPAQuery<>(entityManager);

        List<SalesEntity> salesList = query.from(qSalesEntity)
                .leftJoin(qSalesEntity.customer, qCustomerEntity).fetchJoin()

                .where(SalesPredicate.search(searchDto))
                .orderBy(qSalesEntity.salesDate.asc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(salesList, pageable, query.fetchCount());
    }
}
