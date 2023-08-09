package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.entity.ReconciliationItemEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
@RequiredArgsConstructor
public class ReconciliationValidatorService {

    private final ReconciliationRepository reconciliationRepository;

    public ReconciliationEntity ifFoundByIdReturnElseThrow(Long id) {
        Objects.requireNonNull(id);

        return reconciliationRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Reconciliation not found with id [%s]",id)));

    }
}
