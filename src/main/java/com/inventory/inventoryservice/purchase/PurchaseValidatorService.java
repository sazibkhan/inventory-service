package com.inventory.inventoryservice.purchase;
import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import com.inventory.inventoryservice.purchase.model.PurchaseItemDto;
import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import com.inventory.inventoryservice.supplier.SupplierValidatorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseValidatorService {

    private final PurchaseRepository purchaseRepository;
    private final SupplierValidatorService supplierValidatorService;
    private final ProductValidatorService productValidatorService;

    public PurchaseEntity ifFoundByIdReturnElseThrow(Long id) {
        Objects.requireNonNull(id);
        return purchaseRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Purchase not found with id [%s]",id)));
    }



    public PurchaseEntity validateAndReturnPurchaseSave(PurchaseDto purchaseDto) {

        var entity = PurchaseTransform.toPurchaseEntity(purchaseDto);

        if(ObjectUtils.isNotEmpty(purchaseDto.getSupplier())) {
            entity.setSupplier(supplierValidatorService
                    .ifFoundByIdReturnElseThrow(purchaseDto.getSupplierId()));
        }
        return entity;
    }

    public List<PurchaseItemEntity> validateAndReturnPurchaseItemList(PurchaseDto purchaseDto,
                                                                      PurchaseEntity purchase) {

        return purchaseDto.getItems().stream()
            .map(itm-> {
                PurchaseItemEntity purchaseItem = PurchaseItemTransform.toPurchaseItemEntity(itm);
                purchaseItem.setPurchase(purchase);
                purchaseItem.setProduct(productValidatorService
                    .ifFoundByIdReturnElseThrow(itm.getProductId()));
                purchaseItem.setQuantity(itm.getQuantity().doubleValue());
                return purchaseItem;
            }).collect(Collectors.toList());



    }
}
