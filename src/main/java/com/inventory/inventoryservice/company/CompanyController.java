package com.inventory.inventoryservice.company;

import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/companys")
@RequiredArgsConstructor
public class CompanyController {

    private final  CompanyService companyService;
    @PostMapping
    public ResponseEntity<CompanyRest> saveCompany(@RequestBody CompanyDto companyDto) {
        try {
            CompanyRest companyRest = companyService.saveCompany(companyDto);
            return ResponseEntity.status(HttpStatus.OK).body(companyRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyRest> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        try {
            CompanyRest companyRest = companyService.updateCompany(id, companyDto);
            return ResponseEntity.status(HttpStatus.OK).body(companyRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }



}
