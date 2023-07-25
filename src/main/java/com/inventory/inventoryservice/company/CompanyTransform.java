package com.inventory.inventoryservice.company;
import com.inventory.inventoryservice.company.model.CompanyDto;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.company.model.CompanyRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyTransform {

    public static CompanyEntity toCompanyEntity(CompanyDto companyDto){
        var company=new CompanyEntity();
        BeanUtils.copyProperties(companyDto, company);
        return company;
    }

    public static CompanyRest toCompanyRest(CompanyEntity company ){
        var rest=new CompanyRest();
        BeanUtils.copyProperties(company, rest);
        return rest;
    }
    public static  List<CompanyRest> toCompanyRestList(List<CompanyEntity>list){
        return list.parallelStream()
                .map(CompanyTransform::toCompanyRest)
                .collect(Collectors.toList());
    }


}
