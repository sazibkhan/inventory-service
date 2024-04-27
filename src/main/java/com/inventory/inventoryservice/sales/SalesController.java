package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.sales.model.SalesDto;
import com.inventory.inventoryservice.sales.model.SalesRest;
import com.inventory.inventoryservice.sales.model.SalesSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/sales")
@RequiredArgsConstructor
public class SalesController {

  private final SalesService salesService;

  @PostMapping
  @PreAuthorize("hasAnyRole('SALES_CREATE','ROLE_ADMIN')")
  public ResponseEntity<SalesRest> saveSales(@RequestBody SalesDto salesDto) {
    SalesRest salesRest = salesService.saveSales(salesDto);
    return ResponseEntity.status(HttpStatus.OK).body(salesRest);
  }

  //update Sales
  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('SALES_UPDATE','ROLE_ADMIN')")
  public ResponseEntity<SalesRest> updateSales(@PathVariable Long id, @RequestBody SalesDto salesDto) {
    SalesRest salesRest = salesService.updateSales(id, salesDto);
    return ResponseEntity.status(HttpStatus.OK).body(salesRest);
  }

  //Delete sales > add stock
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('SALES_DELETE','ROLE_ADMIN')")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    salesService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('SALSE_SEARCH','ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody SalesSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(salesService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('SALSE_SEARCH','ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody SalesSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(salesService.searchList(searchDto));
  }


}
