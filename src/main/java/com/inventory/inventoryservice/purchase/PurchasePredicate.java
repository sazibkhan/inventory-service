package com.inventory.inventoryservice.purchase;


import com.inventory.inventoryservice.purchase.model.PurchaseSearchDto;
import com.inventory.inventoryservice.purchase.model.QPurchaseEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class PurchasePredicate {
    private final static QPurchaseEntity qPurchase = QPurchaseEntity.purchaseEntity;

    public static Predicate search(PurchaseSearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if(ObjectUtils.isNotEmpty(searchDto.getId())) {
            builder.and(qPurchase.id.eq(searchDto.getId()));
        }

        if(ObjectUtils.isNotEmpty(searchDto.getPurchaseDate())) {
            builder.and(qPurchase.purchaseDate.eq(searchDto.getPurchaseDate()));
        }

        if(ObjectUtils.isNotEmpty(searchDto.getSupplierId())) {
            builder.and(qPurchase.supplier.id.eq(searchDto.getSupplierId()));
        }
        if(StringUtils.isNotEmpty(searchDto.getSupplier())) {
            builder.and(qPurchase.supplier.supplierName.eq(searchDto.getSupplier()));
        }

        return builder;
    }
}

