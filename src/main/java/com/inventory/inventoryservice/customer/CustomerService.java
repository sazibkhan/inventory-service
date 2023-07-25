package com.inventory.inventoryservice.customer;
import com.inventory.inventoryservice.company.CompanyTransform;
import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.company.model.CompanyRest;
import com.inventory.inventoryservice.customer.model.CustomerDto;
import com.inventory.inventoryservice.customer.model.CustomerEntity;
import com.inventory.inventoryservice.customer.model.CustomerRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final  CustomerValidatorService customerValidatorService;
    public CustomerRest saveCustomer(CustomerDto customerDto){
        CustomerEntity customer=CustomerTransform.toCustomerEntity(customerDto);
        customerRepository.save(customer);
        return  CustomerTransform.toCustomerRest(customer);
    }

    public CustomerRest updateCustomer(Long id, CustomerDto customerDto){
        CustomerEntity customer=customerValidatorService.ifFoundByIdReturnElseThrow(id);
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setCustomerType(customerDto.getCustomerType());
        customer.setEnabled(customerDto.getEnabled());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setCustomerImage(customerDto.getCustomerImage());

        customerRepository.save(customer);
        return  CustomerTransform.toCustomerRest(customer);
    }

    public void deleteCustomer(Long id) {
        CustomerEntity customer=customerValidatorService.ifFoundByIdReturnElseThrow(id);
        customerRepository.deleteById(customer.getId());
    }





}
