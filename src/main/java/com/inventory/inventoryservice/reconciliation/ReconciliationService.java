package com.inventory.inventoryservice.reconciliation;

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

    //todo: save date in reconciliation_items table

    //todo: conditionally increase or decrease stock

    return ReconciliationTransform.toReconciliationRest(reconciliation);
  }

  public void deleteReconciliation(Long id) {

    ReconciliationEntity reconciliation = reconciliationValidatorService.ifFoundByIdReturnElseThrow(id);
    reconciliationRepository.deleteById(reconciliation.getId());

    //todo: find reconciliation items and delete
    //todo: conditionally increase or decrease stock
  }

}
