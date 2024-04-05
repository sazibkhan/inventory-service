package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.reconciliation.model.*;
import com.inventory.inventoryservice.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

  private final ReconciliationRepository reconciliationRepository;
  private final ReconciliationItemRepository reconciliationItemRepository;
  private final ReconciliationValidatorService reconciliationValidatorService;
  private final StockService stockService;

  @Transactional
  public ReconciliationRest saveReconciliation(ReconciliationDto reconciliationDto) {

    ReconciliationEntity reconciliation = ReconciliationTransform.toReconciliationEntity(reconciliationDto);
    reconciliation.setReconciliationStatus(ReconciliationStatusEnum.Pending);
    reconciliation.setEnabled(Boolean.TRUE);
    reconciliationRepository.save(reconciliation);

    List<ReconciliationItemEntity> reconciliationItemList = reconciliationValidatorService
      .validateAndReturnReconciliationItemList(reconciliationDto, reconciliation);
    reconciliationItemRepository.saveAll(reconciliationItemList);

    return ReconciliationTransform.toReconciliationRest(reconciliation);
  }

  @Transactional
  public void approveReconciliation(Long id, ReconciliationApproveRequest request) {

    // update the status of reconciliations
    // filter the WRITE_OFF and decrease stock
    // filter the WRITE_ON and increase stock

    //todo: if already approved throw

    ReconciliationEntity reconciliation = reconciliationValidatorService.ifFoundByIdReturnElseThrow(id);
    List<ReconciliationItemEntity> items = reconciliationItemRepository.findAllByReconciliationId(id);

    reconciliation.setReconciliationStatus(request.getReconciliationStatus());
    reconciliation.setApprovedAt(LocalDateTime.now());
    reconciliationRepository.save(reconciliation);

    if(request.getReconciliationStatus().equals(ReconciliationStatusEnum.Approved)) {
      List<ReconciliationItemEntity> writeOffItems = items.stream()
        .filter(itm -> itm.getReconciliationType().equals(ReconciliationType.WRITE_OFF))
        .collect(Collectors.toList());

      List<ReconciliationItemEntity> writeOnItems = items.stream()
        .filter(itm -> itm.getReconciliationType().equals(ReconciliationType.WRITE_ON))
        .collect(Collectors.toList());

      stockService.decreaseStock(ReconciliationTransform.toStockDto(writeOffItems));
      stockService.increaseStock(ReconciliationTransform.toStockDto(writeOnItems));
    }
  }

  public void deleteReconciliation(Long id) {
    ReconciliationEntity reconciliation = reconciliationValidatorService.ifFoundByIdReturnElseThrow(id);
    if(reconciliation.getReconciliationStatus().equals(ReconciliationStatusEnum.Approved)) {
      List<ReconciliationItemEntity> items = reconciliationItemRepository.findAllByReconciliationId(id);
      List<ReconciliationItemEntity> writeOffItems = items.stream()
        .filter(itm -> itm.getReconciliationType().equals(ReconciliationType.WRITE_OFF))
        .collect(Collectors.toList());
      List<ReconciliationItemEntity> writeOnItems = items.stream()
        .filter(itm -> itm.getReconciliationType().equals(ReconciliationType.WRITE_ON))
        .collect(Collectors.toList());

      stockService.decreaseStock(ReconciliationTransform.toStockDto(writeOnItems));
      stockService.increaseStock(ReconciliationTransform.toStockDto(writeOffItems));

      reconciliationItemRepository.deleteAll(items);
      reconciliationRepository.delete(reconciliation);
    }

  }


}
