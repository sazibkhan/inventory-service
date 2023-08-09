package com.inventory.inventoryservice.reconciliation;


import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationDto;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ReconciliationTransform {

    public static ReconciliationEntity toReconciliationEntity(ReconciliationDto reconciliationDto) {

        var reconciliation = new ReconciliationEntity();
        BeanUtils.copyProperties(reconciliationDto, reconciliation);
        return reconciliation;
    }

    public static ReconciliationRest toReconciliationRest(ReconciliationEntity reconciliation) {
        var rest = new ReconciliationRest();
        BeanUtils.copyProperties(reconciliation, rest);
        return rest;
    }

    public static List<ReconciliationRest> toReconciliationRestList(List<ReconciliationEntity> list) {
        return list.parallelStream()
                .map(ReconciliationTransform::toReconciliationRest)
                .collect(Collectors.toList());

    }
}
