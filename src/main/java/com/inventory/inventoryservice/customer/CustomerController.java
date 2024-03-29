package com.inventory.inventoryservice.customer;

import com.inventory.inventoryservice.customer.model.CustomerDto;
import com.inventory.inventoryservice.customer.model.CustomerRest;
import com.inventory.inventoryservice.customer.model.CustomerSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public ResponseEntity<CustomerRest> saveCustomer(@RequestBody CustomerDto customerDto) {
    CustomerRest customerRest = customerService.saveCustomer(customerDto);
    return ResponseEntity.status(HttpStatus.OK).body(customerRest);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerRest> updateCustomer(@PathVariable Long id,
                                                     @RequestBody CustomerDto customerDt) {
    CustomerRest customerRest = customerService.updateCustomer(id, customerDt);
    return ResponseEntity.status(HttpStatus.OK).body(customerRest);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  public ResponseEntity<?> searchPage(@RequestBody CustomerSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(customerService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  public ResponseEntity<?> searchList(@RequestBody CustomerSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(customerService.searchList(searchDto));
  }

}
