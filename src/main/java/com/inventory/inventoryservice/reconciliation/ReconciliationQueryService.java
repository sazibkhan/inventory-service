package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.purchase.PurchasePredicate;
import com.inventory.inventoryservice.purchase.PurchaseRepository;
import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import com.inventory.inventoryservice.purchase.model.PurchaseSearchDto;
import com.inventory.inventoryservice.purchase.model.QPurchaseEntity;
import com.inventory.inventoryservice.reconciliation.model.QReconciliationEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationSearchDto;
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
public class ReconciliationQueryService {

    private final EntityManager entityManager;
    private final ReconciliationRepository reconciliationRepository;

    public Page<ReconciliationEntity> searchPage(ReconciliationSearchDto searchDto) {

        QReconciliationEntity qReconciliationEntity = QReconciliationEntity.reconciliationEntity;



        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        JPAQuery<PurchaseEntity> query = new JPAQuery<>(entityManager);

      /*  List<ReconciliationEntity> reconciliationList = query.from(qReconciliationEntity)
                .leftJoin(qReconciliationEntity.r, qReconciliationEntity).fetchJoin()
                .where(ReconciliationPredicate.search(searchDto))
                .orderBy(qReconciliationEntity.reconciliationDate.asc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(reconciliationList, pageable, query.fetchCount());
    }*/


//    public List<ReconciliationEntity> searchList(ReconciliationSearchDto searchDto) {
//
//        Predicate predicate = ReconciliationPredicate.search(searchDto);
//        return IterableUtils.toList(reconciliationRepository.findAll(predicate));
//    }

        return null;
    }
}