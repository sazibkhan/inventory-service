package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.company.CompanyTransform;
import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.company.model.CompanyRest;
import com.inventory.inventoryservice.entity.SupplierEntity;
import com.inventory.inventoryservice.supplier.model.SupplierDto;
import com.inventory.inventoryservice.supplier.model.SupplierRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierTransform {

    public static SupplierEntity toSupplierEntity(SupplierDto supplierDto){
        var entity=new SupplierEntity();
        BeanUtils.copyProperties(supplierDto, entity);
        return entity;
    }

    public static SupplierRest toSupplierRest(SupplierEntity entity ){
        var rest=new SupplierRest();
        BeanUtils.copyProperties(entity, rest);
        return rest;
    }
    public static List<SupplierRest> toSupplierRestList(List<SupplierEntity>list){
        return list.parallelStream()
                .map(SupplierTransform::toSupplierRest)
                .collect(Collectors.toList());
    }
}
