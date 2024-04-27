package com.inventory.inventoryservice.supplier;


import com.inventory.inventoryservice.supplier.model.SupplierDto;
import com.inventory.inventoryservice.supplier.model.SupplierRest;
import com.inventory.inventoryservice.supplier.model.SupplierSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

  private final SupplierService supplierService;

  @PostMapping
  @PreAuthorize("hasAnyRole('SUPPLIER_CREATE','ROLE_ADMIN')")
  public ResponseEntity<SupplierRest> saveSupplier(@RequestBody SupplierDto supplierDto) {
    SupplierRest supplierRest = supplierService.saveSupplier(supplierDto);
    return ResponseEntity.status(HttpStatus.OK).body(supplierRest);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('SUPPLIER_UPDATE','ROLE_ADMIN')")
  public ResponseEntity<SupplierRest>updateSupplier(@PathVariable Long id, @RequestBody SupplierDto supplierDto){
    SupplierRest supplierRest = supplierService.updateSupplier(id, supplierDto);
    return ResponseEntity.status(HttpStatus.OK).body(supplierRest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('SUPPLIER_DELETE','ROLE_ADMIN')")
  public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
    supplierService.deleteSupplier(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('SUPPLIER_SEARCH', 'ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody SupplierSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(supplierService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('SUPPLIER_SEARCH', 'ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody SupplierSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(supplierService.searchList(searchDto));
  }

}
