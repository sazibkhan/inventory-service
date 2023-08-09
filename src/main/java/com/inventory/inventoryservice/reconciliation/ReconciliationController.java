package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationDto;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/reconciliations")
@RequiredArgsConstructor
public class ReconciliationController {

    private final ReconciliationService reconciliationService;
    @PostMapping
    public ResponseEntity<ReconciliationRest> saveReconciliation(@RequestBody ReconciliationDto reconciliationDto) {
        try {
            ReconciliationRest reconciliationRest = reconciliationService.saveReconciliation(reconciliationDto);
            return ResponseEntity.status(HttpStatus.OK).body(reconciliationRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReconciliation(@PathVariable Long id) {
        try {
            reconciliationService.deleteReconciliation(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }


}
