package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.category.CategoryTransform;
import com.inventory.inventoryservice.category.model.CategoryDto;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.category.model.CategoryRest;
import com.inventory.inventoryservice.product.ProductValidatorService;
import com.inventory.inventoryservice.purchase.PurchaseItemTransform;
import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import com.inventory.inventoryservice.purchase.model.PurchaseItemEntity;
import com.inventory.inventoryservice.sales.model.*;
import com.inventory.inventoryservice.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

  private final SalesRepository salesRepository;
  private final SalesItemRepository salesItemRepository;
  private final SalesQueryService salesQueryService;
  private final SalesValidatorService salesValidatorService;
  private final StockService stockService;
  @Transactional
  public SalesRest saveSales(SalesDto salesDto) {

    SalesEntity sales = salesValidatorService.validateAndReturnSalesSave(salesDto);
    salesRepository.save(sales);
    List<SalesItemEntity> salesItemList = salesValidatorService.validateAndReturnSalesItemList(salesDto,sales);
    salesItemRepository.saveAll(salesItemList);
    stockService.decreaseStock(SalesItemTransform.toStockDto(salesItemList));

    return SalesTransform.toSalesRest(sales);
  }
  @Transactional
  public SalesRest updateSales(Long id, SalesDto salesDto) {
    List<SalesItemEntity> items = salesItemRepository.findAllBySalesId(id);
    salesItemRepository.deleteAll(items);

    stockService.increaseStock(SalesItemTransform.toStockDto(items));

    SalesEntity sales=salesValidatorService.ifFoundByIdReturnElseThrow(id);
    BeanUtils.copyProperties(salesDto,sales);
    sales.setId(id);
    salesRepository.save(sales);

    List<SalesItemEntity> salesItemList = salesValidatorService.validateAndReturnSalesItemList(salesDto,sales);
    salesItemRepository.saveAll(salesItemList);

    stockService.decreaseStock(SalesItemTransform.toStockDto(salesItemList));

    return SalesTransform.toSalesRest(sales);
  }




  // implement delete method
  @Transactional
  public void delete(Long id) {
    // 1. Delete data from sales_items
  List<SalesItemEntity> items=salesItemRepository.findAllBySalesId(id);
    salesItemRepository.deleteAll(items);
    // 2. Delete data from sales
    SalesEntity sales=salesValidatorService.ifFoundByIdReturnElseThrow(id);
    salesRepository.delete(sales);
    // 3. update stocks
    stockService.increaseStock(SalesItemTransform.toStockDto(items));
  }

  public Page<SalesRest> searchPage(SalesSearchDto searchDto) {
    Page<SalesEntity> page = salesQueryService.searchPage(searchDto);
    List<SalesRest> salesRestList = SalesTransform.toSalesRestList(page.getContent());
    return new PageImpl<>(salesRestList, page.getPageable(), page.getTotalElements());
  }
  public List<SalesRest> searchList(SalesSearchDto searchDto) {
    return SalesTransform.toSalesRestList(salesQueryService.searchList(searchDto));
  }


}
