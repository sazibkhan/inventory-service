package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.reconciliation.model.QReconciliationEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;


public class ReconciliationPredicate {
    private final static QReconciliationEntity qReconciliation = QReconciliationEntity.reconciliationEntity;
    public static Predicate search(ReconciliationSearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if(ObjectUtils.isNotEmpty(searchDto.getId())) {
            builder.and(qReconciliation.id.eq(searchDto.getId()));
        }
        if(ObjectUtils.isNotEmpty(searchDto.getReconciliationDate())) {
            builder.and(qReconciliation.reconciliationDate.eq(searchDto.getReconciliationDate()));
        }
        if(ObjectUtils.isNotEmpty(searchDto.getEnabled())) {
            builder.and(qReconciliation.enabled.eq(searchDto.getEnabled()));
        }
        return builder;
    }

}
