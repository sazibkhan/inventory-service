package com.inventory.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class SearchDto {

  private String searchTerm;
  private Integer page = 0;
  private Integer size = 10;
  private Pageable pageable = PageRequest.of(page, size);
}
