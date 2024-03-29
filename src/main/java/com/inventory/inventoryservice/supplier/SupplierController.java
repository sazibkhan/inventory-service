package com.inventory.inventoryservice.supplier;


import com.inventory.inventoryservice.supplier.model.SupplierDto;
import com.inventory.inventoryservice.supplier.model.SupplierRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

  private final SupplierService supplierService;

  //todo: need to complete the CRUD of Supplier

  @PostMapping
  public ResponseEntity<SupplierRest> saveCompany(@RequestBody SupplierDto supplierDto) {
    SupplierRest supplierRest = supplierService.saveSupplier(supplierDto);
    return ResponseEntity.status(HttpStatus.OK).body(supplierRest);
  }


}
