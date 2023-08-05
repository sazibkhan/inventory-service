package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.company.CompanyRepository;
import com.inventory.inventoryservice.company.model.CompanyEntity;
import com.inventory.inventoryservice.supplier.model.SupplierEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SupplierValidatorService {

    private final SupplierRepository supplierRepository;

    public SupplierEntity ifFoundByIdReturnElseThrow(Long id){
        Objects.requireNonNull(id);

        return  supplierRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(String
                        .format("Supplier not found with id [%s]",id)));
    }
}
