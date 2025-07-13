package com.inventory.inventoryservice.purchase;


import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import com.inventory.inventoryservice.purchase.model.PurchaseSearchDto;
import com.inventory.inventoryservice.purchase.model.QPurchaseEntity;
import com.inventory.inventoryservice.supplier.model.QSupplierEntity;
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
public class PurchaseQueryService {

    private final EntityManager entityManager;
    private final PurchaseRepository purchaseRepository;

    public Page<PurchaseEntity> searchPage(PurchaseSearchDto searchDto) {

        QPurchaseEntity qPurchaseEntity = QPurchaseEntity.purchaseEntity;
        QSupplierEntity qSupplierEntity=QSupplierEntity.supplierEntity;


        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        JPAQuery<PurchaseEntity> query = new JPAQuery<>(entityManager);

        List<PurchaseEntity> purchaseList = query.from(qPurchaseEntity)
                .leftJoin(qPurchaseEntity.supplier, qSupplierEntity).fetchJoin()
                .where(PurchasePredicate.search(searchDto))
                .orderBy(qPurchaseEntity.purchaseDate.asc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(purchaseList, pageable, query.fetchCount());
    }


    public List<PurchaseEntity> searchList(PurchaseSearchDto searchDto) {

        Predicate predicate = PurchasePredicate.search(searchDto);
        return IterableUtils.toList(purchaseRepository.findAll(predicate));
    }
}
