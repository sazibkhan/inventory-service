package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseRest;
import com.inventory.inventoryservice.purchase.model.PurchaseSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

  private final PurchaseService purchaseService;

  @PostMapping
  public ResponseEntity<PurchaseRest> savePurchase(@RequestBody @Valid PurchaseDto purchaseDto) {
    PurchaseRest purchaseRest = purchaseService.savePurchase(purchaseDto);
    return ResponseEntity.status(HttpStatus.OK).body(purchaseRest);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PurchaseRest> updatePurchase(@PathVariable Long id,
                                                     @RequestBody @Valid PurchaseDto purchaseDto) {
    PurchaseRest purchaseRest = purchaseService.updatePurchase(id, purchaseDto);
    return ResponseEntity.status(HttpStatus.OK).body(purchaseRest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePurchase(@PathVariable Long id) {
    purchaseService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  public ResponseEntity<?> searchPage(@RequestBody PurchaseSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(purchaseService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  public ResponseEntity<?> searchList(@RequestBody PurchaseSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(purchaseService.searchList(searchDto));
  }


}


