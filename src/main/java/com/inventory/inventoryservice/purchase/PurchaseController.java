package com.inventory.inventoryservice.purchase;

import com.inventory.inventoryservice.purchase.model.PurchaseDto;
import com.inventory.inventoryservice.purchase.model.PurchaseRest;
import com.inventory.inventoryservice.purchase.model.PurchaseSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    @PostMapping
    public ResponseEntity<PurchaseRest> savePurchase(@RequestBody PurchaseDto purchaseDto) {

        PurchaseRest purchaseRest = purchaseService.savePurchase(purchaseDto);
        return ResponseEntity.status(HttpStatus.OK).body(purchaseRest);
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


