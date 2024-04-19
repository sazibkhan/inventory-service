package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.utils.IterableUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandQueryService {

  private final BrandRepository brandRepository;

  public Page<BrandEntity> searchPage(BrandSearchDto searchDto) {
    Predicate predicate = BrandPredicate.search(searchDto);
    return brandRepository.findAll(predicate, searchDto.getPageable());
  }

  public List<BrandEntity> searchList(BrandSearchDto searchDto) {
    Predicate predicate = BrandPredicate.search(searchDto);
    return IterableUtils.toList(brandRepository.findAll(predicate));
  }

}
