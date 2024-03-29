package com.inventory.inventoryservice.customer;

import com.inventory.inventoryservice.customer.model.CustomerEntity;
import com.inventory.inventoryservice.customer.model.CustomerSearchDto;
import com.inventory.inventoryservice.utils.IterableUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerQueryService {

  private final CustomerRepository customerRepository;

  public Page<CustomerEntity> searchPage(CustomerSearchDto searchDto) {

    Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
    Predicate predicate = CustomerPredicate.search(searchDto);

    return customerRepository.findAll(predicate, pageable);
  }

  public List<CustomerEntity> searchList(CustomerSearchDto searchDto) {

    Predicate predicate = CustomerPredicate.search(searchDto);
    return IterableUtils.toList(customerRepository.findAll(predicate));
  }


}
