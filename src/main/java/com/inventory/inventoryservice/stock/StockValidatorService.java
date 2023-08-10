package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.brand.BrandRepository;
import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.stock.model.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StockValidatorService {

    private final StockRepository stockRepository;

    public StockEntity ifFoundByIdReturnElseThrow(Long id) {
        Objects.requireNonNull(id);

        return stockRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("Stock not found with id [%s]",id)));

    }

}
