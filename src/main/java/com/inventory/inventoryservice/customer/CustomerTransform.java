package com.inventory.inventoryservice.customer;
import com.inventory.inventoryservice.customer.model.CustomerDto;
import com.inventory.inventoryservice.customer.model.CustomerEntity;
import com.inventory.inventoryservice.customer.model.CustomerRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerTransform {


    public static CustomerEntity toCustomerEntity(CustomerDto customerDto){
        var customer = new CustomerEntity();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }
    

    public static CustomerRest toCustomerRest(CustomerEntity customer){
        var rest=new CustomerRest();
        BeanUtils.copyProperties(customer,rest);
        return rest;
    }


    public static List<CustomerRest> toCustomerRestList(List<CustomerEntity> list){
        return  list.parallelStream()
                .map(CustomerTransform::toCustomerRest)
                .collect(Collectors.toList());


    }

}
