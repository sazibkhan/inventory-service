package com.inventory.inventoryservice.customer;

import com.inventory.inventoryservice.customer.model.CustomerDto;
import com.inventory.inventoryservice.customer.model.CustomerRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private  final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerRest> saveCustomer(@RequestBody CustomerDto customerDto) {
        try {
            CustomerRest customerRest = customerService.saveCustomer(customerDto);
            return ResponseEntity.status(HttpStatus.OK).body(customerRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerRest> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDt) {
        try {
            CustomerRest customerRest = customerService.updateCustomer(id, customerDt);
            return ResponseEntity.status(HttpStatus.OK).body(customerRest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error!!");
        }
    }



}
