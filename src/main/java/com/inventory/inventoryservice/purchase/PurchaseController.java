package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseRest;
import com.inventory.inventoryservice.purchase.model.PurchaseSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

  private final PurchaseService purchaseService;

  @PostMapping
  @PreAuthorize("hasAnyRole('PURCHASE_CREATE','ROLE_ADMIN')")
  public ResponseEntity<PurchaseRest> savePurchase(@RequestBody @Valid PurchaseDto purchaseDto) {
    PurchaseRest purchaseRest = purchaseService.savePurchase(purchaseDto);
    return ResponseEntity.status(HttpStatus.OK).body(purchaseRest);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('PURCHASE_UPDATE','ROLE_ADMIN')")
  public ResponseEntity<PurchaseRest> updatePurchase(@PathVariable Long id,
                                                     @RequestBody @Valid PurchaseDto purchaseDto) {
    PurchaseRest purchaseRest = purchaseService.updatePurchase(id, purchaseDto);
    return ResponseEntity.status(HttpStatus.OK).body(purchaseRest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('PURCHASE_DELETE','ROLE_ADMIN')")
  public ResponseEntity<String> deletePurchase(@PathVariable Long id) {
    purchaseService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('PRODUCT_SEARCH','ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody PurchaseSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(purchaseService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('PRODUCT_SEARCH','ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody PurchaseSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(purchaseService.searchList(searchDto));
  }


}


