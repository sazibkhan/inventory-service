package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.reconciliation.model.*;
import com.inventory.inventoryservice.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

  public ReconciliationRest approveReconciliation(Long id ){
    var reconciliation = reconciliationValidatorService.ifFoundByIdReturnElseThrow(id);

    ReconciliationStatusEnum status =ReconciliationStatusEnum.Pending;
    if(status!=null) {
      status = ReconciliationStatusEnum.Pending;
    }else {
      status = ReconciliationStatusEnum.Approved;
    }
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
