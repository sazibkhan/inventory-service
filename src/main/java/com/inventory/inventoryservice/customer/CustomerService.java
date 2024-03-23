package com.inventory.inventoryservice.customer;
import com.inventory.inventoryservice.customer.model.CustomerDto;
import com.inventory.inventoryservice.customer.model.CustomerEntity;
import com.inventory.inventoryservice.customer.model.CustomerRest;
import com.inventory.inventoryservice.customer.model.CustomerSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final  CustomerValidatorService customerValidatorService;
    private final CustomerQueryService customerQueryService;

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

    public Page<CustomerRest> searchPage(CustomerSearchDto searchDto) {

        Page<CustomerEntity> page =  customerQueryService.searchPage(searchDto);
        List<CustomerRest> resultList = CustomerTransform.toCustomerRestList(page.getContent());

        return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
    }

    public List<CustomerRest> searchList(CustomerSearchDto searchDto) {
        return CustomerTransform.toCustomerRestList(customerQueryService.searchList(searchDto));
    }


}
