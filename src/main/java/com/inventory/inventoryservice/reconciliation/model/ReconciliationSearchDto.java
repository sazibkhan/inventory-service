package com.inventory.inventoryservice.reconciliation.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
@Getter
@Setter
@Accessors(chain=true)
public class ReconciliationSearchDto extends SearchDto {

    private Long id;
    private LocalDate reconciliationDate;
    private Boolean enabled;
}
