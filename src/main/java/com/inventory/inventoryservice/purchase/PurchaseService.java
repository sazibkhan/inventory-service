package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.purchase.model.*;
import com.inventory.inventoryservice.sales.SalesItemEntity;
import com.inventory.inventoryservice.sales.SalesItemTransform;
import com.inventory.inventoryservice.sales.SalesTransform;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import com.inventory.inventoryservice.sales.model.SalesRest;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseQueryService purchaseQueryService;
    private final PurchaseValidatorService purchaseValidatorService;
    private final ProductValidatorService productValidatorService;

    @Transactional
    public PurchaseRest savePurchase(PurchaseDto purchaseDto){
        PurchaseEntity purchase=purchaseValidatorService.validateAndReturnPurchaseSave(purchaseDto);
        purchaseRepository.save(purchase);


     purchaseDto.getItems().forEach(purchaseItenDto->{
         PurchaseItemEntity purchaseItem=PurchaseItemTransform.toPurchaseItemEntity(purchaseItenDto);
         purchaseItem.setPurchase(purchase);
         purchaseItem.setProduct(productValidatorService
                 .ifFoundByIdReturnElseThrow(purchaseItenDto.getProductId()));
         purchaseItemRepository.save(purchaseItem);
     });
    return  PurchaseTransform.toPurchaseRest(purchase);

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
