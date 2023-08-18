package com.inventory.inventoryservice.stock;

import com.inventory.inventoryservice.stock.model.QStockEntity;
import com.inventory.inventoryservice.stock.model.StockSearchDto;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


public class StockPredicate {

 private  final static QStockEntity qSTOCK=QStockEntity.stockEntity;


 public  static Predicate search(StockSearchDto searchDto) {

     BooleanBuilder builder = new BooleanBuilder();

     if (ObjectUtils.isNotEmpty(searchDto.getId())) {
         builder.and(qSTOCK.id.eq(searchDto.getId()));
     }

     if(ObjectUtils.isNotEmpty(searchDto.getCurrentStock())) {
         builder.and(qSTOCK.currentStock.eq(searchDto.getCurrentStock()));
     }
     if(ObjectUtils.isNotEmpty(searchDto.getProductId())) {
         builder.and(qSTOCK.product.id.eq(searchDto.getProductId()));
     }
     if(StringUtils.isNotEmpty(searchDto.getProduct())) {
         builder.and(qSTOCK.product.productName.eq(searchDto.getProduct()));
     }


     return builder;
 }








}
