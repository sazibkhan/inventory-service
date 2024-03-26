package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.purchase.PurchaseItemTransform;
import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import com.inventory.inventoryservice.reconciliation.model.*;
import com.inventory.inventoryservice.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

  private final ReconciliationRepository reconciliationRepository;
  private final ReconciliationItemRepository reconciliationItemRepository;
  private final ReconciliationValidatorService reconciliationValidatorService;
  private final StockService stockService;

  public ReconciliationRest saveReconciliation(ReconciliationDto reconciliationDto) {

    ReconciliationEntity reconciliation = ReconciliationTransform.toReconciliationEntity(reconciliationDto);
    reconciliationRepository.save(reconciliation);

    //Save date in reconciliation_items table
    List<ReconciliationItemEntity> reconciliationItemList = reconciliationValidatorService
            .validateAndReturnReconciliationItemList(reconciliationDto,reconciliation);
    reconciliationItemRepository.saveAll(reconciliationItemList);

    // conditionally increase or decrease stock
    stockService.increaseStock(ReconciliationItemTransform.toStockDto(reconciliationItemList));

    return ReconciliationTransform.toReconciliationRest(reconciliation);
  }

  public void deleteReconciliation(Long id) {
    List<ReconciliationItemEntity> items=reconciliationItemRepository.findAllByReconciliationId(id);
    reconciliationItemRepository.deleteAll(items);
    ReconciliationEntity reconciliation = reconciliationValidatorService.ifFoundByIdReturnElseThrow(id);
    reconciliationRepository.delete(reconciliation);
    // conditionally increase or decrease stock
    stockService.decreaseStock(ReconciliationItemTransform.toStockDto(items));

  }

}
