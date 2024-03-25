package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import com.inventory.inventoryservice.reconciliation.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

  private final ReconciliationRepository reconciliationRepository;
  private final ReconciliationItemRepository reconciliationItemRepository;
  private final ReconciliationValidatorService reconciliationValidatorService;

  public ReconciliationRest saveReconciliation(ReconciliationDto reconciliationDto) {

    ReconciliationEntity reconciliation = ReconciliationTransform.toReconciliationEntity(reconciliationDto);
    reconciliationRepository.save(reconciliation);

    //Save date in reconciliation_items table
    List<ReconciliationItemEntity> reconciliationItemList = reconciliationValidatorService
            .validateAndReturnReconciliationItemList(reconciliationDto,reconciliation);
    reconciliationItemRepository.saveAll(reconciliationItemList);
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
