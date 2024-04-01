package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.supplier.model.QSupplierEntity;
import com.inventory.inventoryservice.supplier.model.SupplierSearchDto;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class SupplierPredicate {
    private  final static QSupplierEntity  qSupplier = QSupplierEntity.supplierEntity;
    public  static BooleanBuilder search(SupplierSearchDto searchDto){

        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.isNotEmpty(searchDto.getSupplierName())){
            builder.and(qSupplier.supplierName.eq(searchDto.getSupplierName()));
        }
        if(StringUtils.isNotEmpty(searchDto.getSupplierAddress())){
            builder.and(qSupplier.supplierAddress.eq(searchDto.getSupplierAddress()));
        }
        if(StringUtils.isNotEmpty(searchDto.getPhoneNumber())){
            builder.and(qSupplier.phoneNumber.eq(searchDto.getPhoneNumber()));
        } if(ObjectUtils.isNotEmpty(searchDto.getEnabled())) {
            builder.and(qSupplier.enabled.eq(searchDto.getEnabled()));
        }

    return builder;
    }








}
