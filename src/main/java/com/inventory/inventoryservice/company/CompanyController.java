package com.inventory.inventoryservice.company;

import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyRest;
import com.inventory.inventoryservice.company.model.CompanySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/companys")
@RequiredArgsConstructor
public class CompanyController {

  private final CompanyService companyService;

  @PostMapping
  public ResponseEntity<CompanyRest> saveCompany(@RequestBody CompanyDto companyDto) {

    CompanyRest companyRest = companyService.saveCompany(companyDto);
    return ResponseEntity.status(HttpStatus.OK).body(companyRest);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CompanyRest> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto) {

    CompanyRest companyRest = companyService.updateCompany(id, companyDto);
    return ResponseEntity.status(HttpStatus.OK).body(companyRest);

  }
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCompany(@PathVariable Long id) {

    companyService.deleteCompany(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");

  }

  @PostMapping("/search-page")
  public ResponseEntity<?> searchPage(@RequestBody CompanySearchDto searchDto) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(companyService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  public ResponseEntity<?> searchList(@RequestBody CompanySearchDto searchDto) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(companyService.searchList(searchDto));
  }

}