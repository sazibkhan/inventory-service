package com.inventory.inventoryservice.company;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompanyValidatorService {

    private final  CompanyRepository companyRepository;

    public CompanyEntity ifFoundByIdReturnElseThrow(Long id){
        Objects.requireNonNull(id);

        return  companyRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(String
                        .format(" Company not found with id [%s]",id)));
    }





}
