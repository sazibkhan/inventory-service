package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.supplier.model.SupplierDto;
import com.inventory.inventoryservice.supplier.model.SupplierEntity;
import com.inventory.inventoryservice.supplier.model.SupplierRest;
import com.inventory.inventoryservice.supplier.model.SupplierTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

  private final SupplierRepository supplierRepository;
  private final SupplierValidatorService supplierValidatorService;

  public SupplierRest saveSupplier(SupplierDto supplierDto) {
    SupplierEntity supplierEntity = SupplierTransform.toSupplierEntity(supplierDto);
    supplierEntity.setSupplierType(SupplierTypeEnum.Company);
    supplierEntity.setEnabled(Boolean.TRUE);
    supplierEntity.setCreatedAt(LocalDateTime.now());

    supplierRepository.save(supplierEntity);
    return SupplierTransform.toSupplierRest(supplierEntity);
  }

  public SupplierRest updateSupplier(Long id, SupplierDto supplierDto){
    SupplierEntity supplier = supplierValidatorService.ifFoundByIdReturnElseThrow(id);

    supplier.setSupplierName(supplierDto.getSupplierName());
    supplier.setSupplierAddress(supplierDto.getSupplierAddress());
    supplier.setPhoneNumber(supplierDto.getPhoneNumber());
    supplier.setUpdatedAt(LocalDateTime.now());

    supplierRepository.save(supplier);
    return  SupplierTransform.toSupplierRest(supplier);
  }

  public void deleteSupplier(Long id) {
    SupplierEntity supplier = supplierValidatorService.ifFoundByIdReturnElseThrow(id);
    supplierRepository.deleteById(supplier.getId());
  }





}
