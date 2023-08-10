package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.stock.model.StockDto;
import com.inventory.inventoryservice.stock.model.StockEntity;
import com.inventory.inventoryservice.stock.model.StockRest;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class StockTransform {

    public static StockEntity toStockEntity(StockDto stockDto) {
        var stock = new StockEntity();
        BeanUtils.copyProperties(stockDto, stock);
        return stock;
    }

    public static StockRest toStockRest(StockEntity stock) {
        var rest = new StockRest();
        BeanUtils.copyProperties(stock, rest);
        return rest;
    }

    public static List<StockRest> toStockRestList(List<StockEntity> list) {
        return list.parallelStream()
                .map(StockTransform::toStockRest)
                .collect(Collectors.toList());

    }
}
