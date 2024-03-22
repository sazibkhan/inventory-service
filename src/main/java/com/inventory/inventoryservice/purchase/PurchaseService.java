package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.purchase.model.*;
import com.inventory.inventoryservice.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final PurchaseItemRepository purchaseItemRepository;
  private final PurchaseQueryService purchaseQueryService;
  private final PurchaseValidatorService purchaseValidatorService;
  private final StockService stockService;

  @Transactional
  public PurchaseRest savePurchase(PurchaseDto purchaseDto) {

    PurchaseEntity purchase = purchaseValidatorService.validateAndReturnPurchaseSave(purchaseDto);

    purchaseRepository.save(purchase);

    List<PurchaseItemEntity> purchaseItemList = purchaseValidatorService
        .validateAndReturnPurchaseItemList(purchaseDto, purchase);

    purchaseItemRepository.saveAll(purchaseItemList);

    stockService.increaseStock(PurchaseItemTransform.toStockDto(purchaseItemList));


    return PurchaseTransform.toPurchaseRest(purchase);
  }

  @Transactional
  public void delete(Long id) {

    // 1. delete data from purchase_items
    // 2. delete data from purchases
    // 3. update stocks

    List<PurchaseItemEntity> items = purchaseItemRepository.findAllByPurchaseId(id);
    purchaseItemRepository.deleteAll(items);

    PurchaseEntity purchase = purchaseValidatorService.ifFoundByIdReturnElseThrow(id);
    purchaseRepository.delete(purchase);

    stockService.decreaseStock(PurchaseItemTransform.toStockDto(items));

  }


  public Page<PurchaseRest> searchPage(PurchaseSearchDto searchDto) {

    Page<PurchaseEntity> page = purchaseQueryService.searchPage(searchDto);
    List<PurchaseRest> purchaseRestList = PurchaseTransform.toPurchaseRestList(page.getContent());

    return new PageImpl<>(purchaseRestList, page.getPageable(), page.getTotalElements());
  }

  public List<PurchaseRest> searchList(PurchaseSearchDto searchDto) {
    return PurchaseTransform.toPurchaseRestList(purchaseQueryService.searchList(searchDto));
  }
}
