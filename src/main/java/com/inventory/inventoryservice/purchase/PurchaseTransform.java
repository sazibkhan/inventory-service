package com.inventory.inventoryservice.purchase;


import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import com.inventory.inventoryservice.purchase.model.PurchaseRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseTransform {
    public static PurchaseEntity toPurchaseEntity(PurchaseDto purchaseDto) {
        var sales = new PurchaseEntity();
        BeanUtils.copyProperties(purchaseDto,sales);
        return sales;
    }

    public static PurchaseRest toPurchaseRest(PurchaseEntity purchase) {
        var rest = new PurchaseRest();
        BeanUtils.copyProperties(purchase, rest);

//        Optional.ofNullable(sales.getCustomer()).ifPresent(customer-> {
//            rest.setCustomerName(customer.getCustomerName());
//        });

        return rest;
    }

    public static List<PurchaseRest> toPurchaseRestList(List<PurchaseEntity> list) {
        return list.stream()
                .map(PurchaseTransform::toPurchaseRest)
                .collect(Collectors.toList());

    }
}
