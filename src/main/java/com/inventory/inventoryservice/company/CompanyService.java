package com.inventory.inventoryservice.company;

import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.company.model.CompanyRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final  CompanyValidatorService companyValidatorService;

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
}
