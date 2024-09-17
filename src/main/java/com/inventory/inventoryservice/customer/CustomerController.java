package com.inventory.inventoryservice.customer;

import com.inventory.inventoryservice.customer.model.CustomerDto;
import com.inventory.inventoryservice.customer.model.CustomerRest;
import com.inventory.inventoryservice.customer.model.CustomerSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  @PreAuthorize("hasAnyRole('CUSTOMER_CREATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<CustomerRest> saveCustomer(@RequestBody CustomerDto customerDto) {
    CustomerRest customerRest = customerService.saveCustomer(customerDto);
    return ResponseEntity.status(HttpStatus.OK).body(customerRest);
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAnyRole('CUSTOMER_GET','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<CustomerRest> getCustomerById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('CUSTOMER_UPDATE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<CustomerRest> updateCustomer(@PathVariable Long id,
                                                     @RequestBody CustomerDto customerDt) {
    CustomerRest customerRest = customerService.updateCustomer(id, customerDt);
    return ResponseEntity.status(HttpStatus.OK).body(customerRest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('CUSTOMER_DELETE','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('CUSTOMER_SEARCH','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody CustomerSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(customerService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('CUSTOMER_SEARCH','ROLE_SUPER_ADMIN','ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody CustomerSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(customerService.searchList(searchDto));
  }

}
