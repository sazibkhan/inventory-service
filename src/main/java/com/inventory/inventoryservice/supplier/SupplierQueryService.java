package com.inventory.inventoryservice.supplier;

import com.inventory.inventoryservice.brand.BrandPredicate;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.brand.model.BrandSearchDto;
import com.inventory.inventoryservice.supplier.model.SupplierEntity;
import com.inventory.inventoryservice.supplier.model.SupplierSearchDto;
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
public class SupplierQueryService {

    private  final SupplierRepository supplierRepository;

    public Page<SupplierEntity> searchPage(SupplierSearchDto searchDto) {

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());
        Predicate predicate = SupplierPredicate.search(searchDto);

        return supplierRepository.findAll(predicate, pageable);
    }

    public List<SupplierEntity> searchList(SupplierSearchDto searchDto) {

        Predicate predicate = SupplierPredicate.search(searchDto);

        return IterableUtils.toList(supplierRepository.findAll(predicate));
    }

}
