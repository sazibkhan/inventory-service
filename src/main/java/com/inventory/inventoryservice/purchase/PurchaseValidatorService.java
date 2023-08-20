package com.inventory.inventoryservice.purchase;
import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import com.inventory.inventoryservice.supplier.SupplierValidatorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PurchaseValidatorService {

    private final PurchaseRepository purchaseRepository;
    private final SupplierValidatorService supplierValidatorService;

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
}
