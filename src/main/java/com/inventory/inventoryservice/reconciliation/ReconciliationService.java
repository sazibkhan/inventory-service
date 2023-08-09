package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.BrandValidatorService;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationDto;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

    private final ReconciliationRepository reconciliationRepository;
    private final ReconciliationValidatorService reconciliationValidatorService;

    public ReconciliationRest saveReconciliation(ReconciliationDto reconciliationDto) {

        ReconciliationEntity reconciliation = ReconciliationTransform.toReconciliationEntity(reconciliationDto);
        reconciliationRepository.save(reconciliation);
        return ReconciliationTransform.toReconciliationRest(reconciliation);
    }

    public void deleteReconciliation(Long id) {

        ReconciliationEntity reconciliation = reconciliationValidatorService.ifFoundByIdReturnElseThrow(id);
        reconciliationRepository.deleteById(reconciliation.getId());
    }

}
