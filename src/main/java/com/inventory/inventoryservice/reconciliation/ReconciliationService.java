package com.inventory.inventoryservice.reconciliation;

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
    reconciliation.setReconciliationStatus(ReconciliationStatusEnum.Pending);
    reconciliation.setEnabled(Boolean.TRUE);
    reconciliationRepository.save(reconciliation);

    List<ReconciliationItemEntity> reconciliationItemList = reconciliationValidatorService
      .validateAndReturnReconciliationItemList(reconciliationDto, reconciliation);
    reconciliationItemRepository.saveAll(reconciliationItemList);

    stockService.increaseStock(ReconciliationItemTransform.toStockDto(reconciliationItemList));

    return ReconciliationTransform.toReconciliationRest(reconciliation);
  }

  public void deleteReconciliation(Long id) {
    List<ReconciliationItemEntity> items = reconciliationItemRepository.findAllByReconciliationId(id);
    reconciliationItemRepository.deleteAll(items);

    ReconciliationEntity reconciliation = reconciliationValidatorService.ifFoundByIdReturnElseThrow(id);
    reconciliationRepository.delete(reconciliation);
    // conditionally increase or decrease stock
    stockService.decreaseStock(ReconciliationItemTransform.toStockDto(items));

  }

}
