package com.inventory.inventoryservice.supplier;


import com.inventory.inventoryservice.company.CompanyService;
import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyRest;
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
    @PostMapping
    public ResponseEntity<SupplierRest> saveCompany(@RequestBody SupplierDto supplierDto) {
        try {
            SupplierRest supplierRest = supplierService.saveSupplier(supplierDto);
            return ResponseEntity.status(HttpStatus.OK).body(supplierRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }



}
