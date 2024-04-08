package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.supplier.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

  private final SupplierRepository supplierRepository;
  private final SupplierValidatorService supplierValidatorService;
  private final SupplierQueryService supplierQueryService;

  public SupplierRest saveSupplier(SupplierDto supplierDto) {
    SupplierEntity supplier = SupplierTransform.toSupplierEntity(supplierDto);
    supplier.setEnabled(Boolean.TRUE);

    supplierRepository.save(supplier);
    return SupplierTransform.toSupplierRest(supplier);
  }

  public SupplierRest updateSupplier(Long id, SupplierDto supplierDto){
    SupplierEntity supplier = supplierValidatorService.ifFoundByIdReturnElseThrow(id);
    supplier.setSupplierName(supplierDto.getSupplierName());
    supplier.setSupplierAddress(supplierDto.getSupplierAddress());
    supplier.setPhoneNumber(supplierDto.getPhoneNumber());
    supplierRepository.save(supplier);
    return  SupplierTransform.toSupplierRest(supplier);
  }

  public void deleteSupplier(Long id) {
    SupplierEntity supplier = supplierValidatorService.ifFoundByIdReturnElseThrow(id);
    supplierRepository.deleteById(supplier.getId());
  }

  public Page<SupplierRest> searchPage(SupplierSearchDto searchDto) {
    Page<SupplierEntity> page =  supplierQueryService.searchPage(searchDto);
    List<SupplierRest> resultList = SupplierTransform.toSupplierRestList(page.getContent());
    return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
  }

  public List<SupplierRest> searchList(SupplierSearchDto searchDto) {
    return SupplierTransform.toSupplierRestList(supplierQueryService.searchList(searchDto));
  }

}
