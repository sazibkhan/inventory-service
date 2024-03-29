package com.inventory.inventoryservice.reconciliation;

import com.inventory.inventoryservice.reconciliation.model.ReconciliationDto;
import com.inventory.inventoryservice.reconciliation.model.ReconciliationRest;
import com.inventory.inventoryservice.sales.model.SalesDto;
import com.inventory.inventoryservice.sales.model.SalesRest;
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
        ReconciliationRest reconciliationRest = reconciliationService.saveReconciliation(reconciliationDto);
        return ResponseEntity.status(HttpStatus.OK).body(reconciliationRest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReconciliationRest> approveReconciliation(@PathVariable Long id) {
        ReconciliationRest reconciliationRest = reconciliationService.approveReconciliation(id);
        return ResponseEntity.status(HttpStatus.OK).body(reconciliationRest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReconciliation(@PathVariable Long id) {
        reconciliationService.deleteReconciliation(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
    }




}
