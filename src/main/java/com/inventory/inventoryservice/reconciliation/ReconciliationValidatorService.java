package com.inventory.inventoryservice.reconciliation;


import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationDto;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationEntity;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReconciliationValidatorService {

  private final ReconciliationRepository reconciliationRepository;
  private final ProductValidatorService productValidatorService;

  public ReconciliationEntity ifFoundByIdReturnElseThrow(Long id) {
    Objects.requireNonNull(id);
    return reconciliationRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException(String
        .format("Reconciliation not found with id [%s]", id)));
  }

  public List<ReconciliationItemEntity> validateAndReturnReconciliationItemList(ReconciliationDto reconciliationDto, ReconciliationEntity reconciliation) {
    return reconciliationDto.getItems().stream()
      .map(itm -> {
        ReconciliationItemEntity reconciliationItem = ReconciliationItemTransform.toReconciliationItemEntity(itm);
        reconciliationItem.setReconciliation(reconciliation);
        reconciliationItem.setProduct(productValidatorService.ifFoundByIdReturnElseThrow(itm.getProductId()));
        reconciliationItem.setQuantity(itm.getQuantity());
        reconciliationItem.setEnabled(Boolean.TRUE);
        return reconciliationItem;
      }).collect(Collectors.toList());
  }
}
