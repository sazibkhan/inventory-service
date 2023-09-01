package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.stock.model.StockDto;
import com.inventory.inventoryservice.stock.model.StockEntity;
import com.inventory.inventoryservice.stock.model.StockRest;
import com.inventory.inventoryservice.stock.model.StockSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockQueryService stockQueryService;

    //increase stock

    public void increaseStock(List<StockDto> items) {

        // if Product exists then update the stock i.e: 100+10=110

        // if Product not found then save new stock with i.e: 20=20

    }



    // decrease stock

    public void decreaseStock(List<StockDto> items) {

        // if Product exists then update the stock i.e: 100-10=110

        // if Product not found then throw exception

    }


    public Page<StockRest> searchPage(StockSearchDto searchDto) {

        Page<StockEntity> page = stockQueryService.searchPage(searchDto);

        List<StockRest> stockRestList = StockTransform.toStockRestList(page.getContent());

        return new PageImpl<>(stockRestList, page.getPageable(), page.getTotalElements());

    }

    public List<StockRest> searchList(StockSearchDto searchDto) {
        return StockTransform.toStockRestList(stockQueryService.searchList(searchDto));
    }


}

