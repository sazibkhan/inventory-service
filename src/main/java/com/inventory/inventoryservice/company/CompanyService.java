package com.inventory.inventoryservice.company;

import com.inventory.inventoryservice.brand.BrandTransform;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandRest;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.company.model.CompanyRest;
import com.inventory.inventoryservice.company.model.CompanySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final  CompanyValidatorService companyValidatorService;
    private  final CompanyQueryService companyQueryService;

    public CompanyRest saveCompany(CompanyDto companyDto){

        CompanyEntity company=CompanyTransform.toCompanyEntity(companyDto);
        companyRepository.save(company);

        return CompanyTransform.toCompanyRest(company);
    }


    public CompanyRest updateCompany(Long id, CompanyDto companyDto){

        CompanyEntity company=companyValidatorService.ifFoundByIdReturnElseThrow(id);

        company.setCompanyName(companyDto.getCompanyName());
        company.setAddress(companyDto.getAddress());
        company.setEmail(companyDto.getEmail());
        company.setMobile(companyDto.getMobile());
        company.setEnabled(companyDto.getEnabled());
        companyRepository.save(company);

        return CompanyTransform.toCompanyRest(company);
    }

    public void deleteCompany(Long id) {

        CompanyEntity company=companyValidatorService.ifFoundByIdReturnElseThrow(id);
        companyRepository.deleteById(company.getId());
    }




    public Page<CompanyRest> searchPage(CompanySearchDto searchDto) {

        Page<CompanyEntity> page =  companyQueryService.searchPage(searchDto);
        List<CompanyRest> resultList = CompanyTransform.toCompanyRestList(page.getContent());

        return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
    }

    public List<CompanyRest> searchList(CompanySearchDto searchDto) {
        return CompanyTransform.toCompanyRestList(companyQueryService.searchList(searchDto));
    }






}
