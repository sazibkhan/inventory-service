package com.inventory.inventoryservice.company;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.company.model.CompanySearchDto;
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
public class CompanyQueryService {

  private final CompanyRepository companyRepository;

  public Page<CompanyEntity> searchPage(CompanySearchDto searchDto) {

    Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
    Predicate predicate = CompanyPredicate.search(searchDto);

    return companyRepository.findAll(predicate, pageable);
  }

  public List<CompanyEntity> searchList(CompanySearchDto searchDto) {

    Predicate predicate = CompanyPredicate.search(searchDto);
    return IterableUtils.toList(companyRepository.findAll(predicate));
  }
}
