package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.product.model.QProductEntity;
import com.inventory.inventoryservice.sales.SalesPredicate;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import com.inventory.inventoryservice.stock.model.QStockEntity;
import com.inventory.inventoryservice.stock.model.StockEntity;
import com.inventory.inventoryservice.stock.model.StockSearchDto;
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
public class StockQueryService {

    private final EntityManager entityManager;
    private final StockRepository stockRepository;

    public Page<StockEntity> searchPage(StockSearchDto searchDto){

        QStockEntity qStockEntity=QStockEntity.stockEntity;
        QProductEntity qProductEntity=QProductEntity.productEntity;

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        JPAQuery<StockEntity> query = new JPAQuery<>(entityManager);

        List<StockEntity> stockList=query.from(qStockEntity)
                .leftJoin(qStockEntity.product, qProductEntity).fetchJoin()
                .where(StockPredicate.search(searchDto))
                .orderBy(qStockEntity.currentStock.asc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(stockList, pageable, query.fetchCount());
    }


    public List<StockEntity> searchList(StockSearchDto searchDto) {

        Predicate predicate = StockPredicate.search(searchDto);
        return IterableUtils.toList(stockRepository.findAll(predicate));
    }

}
