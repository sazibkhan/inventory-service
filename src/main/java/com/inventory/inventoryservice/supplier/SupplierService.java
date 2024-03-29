package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.supplier.model.SupplierDto;
import com.inventory.inventoryservice.supplier.model.SupplierEntity;
import com.inventory.inventoryservice.supplier.model.SupplierRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService {

  private final SupplierRepository supplierRepository;
  private final SupplierValidatorService supplierValidatorService;

  public SupplierRest saveSupplier(SupplierDto supplierDto) {
    SupplierEntity supplierEntity = SupplierTransform.toSupplierEntity(supplierDto);
    supplierRepository.save(supplierEntity);

    return SupplierTransform.toSupplierRest(supplierEntity);
  }

}
