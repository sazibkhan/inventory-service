package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.customer.model.QCustomerEntity;
import com.inventory.inventoryservice.sales.model.QSalesEntity;
import com.inventory.inventoryservice.sales.model.QSalesItemEntity;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import com.inventory.inventoryservice.utils.IterableUtils;
import com.querydsl.core.types.Predicate;
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
public class SalesQueryService{

  private final EntityManager entityManager;
  private final SalesRepository salesRepository;

  public Page<SalesEntity> searchPage(SalesSearchDto searchDto){

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

  public Page<SalesEntity> searchPageWithDetail(SalesSearchDto searchDto){

    QSalesEntity qSalesEntity = QSalesEntity.salesEntity;
    QCustomerEntity qCustomerEntity = QCustomerEntity.customerEntity;
    QSalesItemEntity qSalesItemEntity = QSalesItemEntity.salesItemEntity;

    Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
    JPAQuery<SalesEntity> query = new JPAQuery<>(entityManager);

    List<SalesEntity> salesList = query.from(qSalesEntity)
      .leftJoin(qSalesEntity.customer, qCustomerEntity).fetchJoin()
      .leftJoin(qSalesEntity.items, qSalesItemEntity).fetchJoin()
      .where(SalesPredicate.search(searchDto))
      .orderBy(qSalesEntity.salesDate.asc())
      .limit(pageable.getPageSize()).offset(pageable.getOffset())
      .fetch();

    return new PageImpl<>(salesList, pageable, query.fetchCount());
  }

  public List<SalesEntity> searchList(SalesSearchDto searchDto){

    Predicate predicate = SalesPredicate.search(searchDto);
    return IterableUtils.toList(salesRepository.findAll(predicate));
  }

  public List<SalesEntity> searchListWithDetail(SalesSearchDto searchDto){

    QSalesEntity qSalesEntity = QSalesEntity.salesEntity;
    QCustomerEntity qCustomerEntity = QCustomerEntity.customerEntity;
    QSalesItemEntity qSalesItemEntity = QSalesItemEntity.salesItemEntity;

    return new JPAQuery<SalesEntity>(entityManager).from(qSalesEntity)
      .leftJoin(qSalesEntity.customer, qCustomerEntity).fetchJoin()
      .leftJoin(qSalesEntity.items, qSalesItemEntity).fetchJoin()
      .where(SalesPredicate.search(searchDto))
      .orderBy(qSalesEntity.salesDate.asc())
      .fetch();
  }

  public List<SalesEntity> getSalesWithDetailById(Long id){

    QSalesEntity qSalesEntity = QSalesEntity.salesEntity;
    QCustomerEntity qCustomerEntity = QCustomerEntity.customerEntity;
    QSalesItemEntity qSalesItemEntity = QSalesItemEntity.salesItemEntity;

    return new JPAQuery<SalesEntity>(entityManager).from(qSalesEntity)
      .leftJoin(qSalesEntity.customer, qCustomerEntity).fetchJoin()
      .leftJoin(qSalesEntity.items, qSalesItemEntity).fetchJoin()
      .where(qSalesEntity.id.eq(id))
      .fetch();
  }
}
