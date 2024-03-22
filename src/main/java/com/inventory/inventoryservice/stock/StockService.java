package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.stock.model.StockDto;
import com.inventory.inventoryservice.stock.model.StockEntity;
import com.inventory.inventoryservice.stock.model.StockRest;
import com.inventory.inventoryservice.stock.model.StockSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockQueryService stockQueryService;
    private final StockRepository stockRepository;
    private final ProductValidatorService productValidatorService;

    //increase stock

    public void increaseStock(List<StockDto> items) {

        items.forEach(item-> {
            Optional<StockEntity> stockEntityOptional = stockRepository.findByProductId(item.getProductId());
          StockEntity stock;
          if(stockEntityOptional.isPresent()) {
            stock = stockEntityOptional.get();
            stock.setCurrentStock(stock.getCurrentStock()+item.getCurrentStock());
          } else {
            stock = new StockEntity();
                stock.setProduct(productValidatorService.ifFoundByIdReturnElseThrow(item.getProductId()));
                stock.setCurrentStock(item.getCurrentStock());
          }
          stockRepository.save(stock);
        });

    }



    // decrease stock

    public void decreaseStock(List<StockDto> items) {

      items.forEach(item-> {
        Optional<StockEntity> stockEntityOptional = stockRepository.findByProductId(item.getProductId());
        StockEntity stock = stockEntityOptional.orElseThrow();
        stock.setCurrentStock(stock.getCurrentStock() - item.getCurrentStock());
        stockRepository.save(stock);
      });

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

