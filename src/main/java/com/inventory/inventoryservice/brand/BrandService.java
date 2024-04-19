package com.inventory.inventoryservice.brand;

import com.inventory.inventoryservice.brand.mapper.BrandRestMapper;
import com.inventory.inventoryservice.brand.model.BrandDto;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService{

  private final BrandRepository brandRepository;
  private final BrandQueryService brandQueryService;
  private final BrandValidatorService brandValidatorService;
  private final BrandRestMapper brandRestMapper;

  public BrandRest saveBrand(BrandDto brandDto){
    BrandEntity brand = BrandTransform.toBrandEntity(brandDto);
    brand.setEnabled(Boolean.TRUE);
    brandRepository.save(brand);
    return BrandTransform.toBrandRest(brand);
  }

  public BrandRest getBrandById(Long id){
    BrandEntity brand = brandValidatorService.ifFoundByIdReturnElseThrow(id);
    return brandRestMapper.apply(brand);
  }

  public BrandRest updateBrand(Long id, BrandDto brandDto){
    BrandEntity brand = brandValidatorService.ifFoundByIdReturnElseThrow(id);
    brand.setBrandName(brandDto.getBrandName());
    brand.setEnabled(brandDto.getEnabled());
    brandRepository.save(brand);
    return brandRestMapper.apply(brand);
  }

  public void deleteBrand(Long id){
    BrandEntity brand = brandValidatorService.ifFoundByIdReturnElseThrow(id);
    brandRepository.deleteById(brand.getId());
  }

  public Page<BrandRest> searchPage(BrandSearchDto searchDto){
    Page<BrandEntity> page = brandQueryService.searchPage(searchDto);
    List<BrandRest> resultList = page.getContent().stream().map(brandRestMapper).collect(Collectors.toList());
    return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
  }

  public List<BrandRest> searchList(BrandSearchDto searchDto){
    return BrandTransform.toBrandRestList(brandQueryService.searchList(searchDto));
  }

}
