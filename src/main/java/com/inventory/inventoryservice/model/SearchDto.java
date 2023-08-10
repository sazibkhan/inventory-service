package com.inventory.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class SearchDto {

  private String searchTerm;
  private Integer page = 0;
  private Integer size = 10;
}
