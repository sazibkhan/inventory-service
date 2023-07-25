package com.inventory.inventoryservice.customer;

import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.customer.model.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerValidatorService {

    private  final CustomerRepository customerRepository;


    public CustomerEntity ifFoundByIdReturnElseThrow(Long id){
        Objects.requireNonNull(id);

        return  customerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(String
                        .format(" Customer not found with id [%s]",id)));
    }

}
