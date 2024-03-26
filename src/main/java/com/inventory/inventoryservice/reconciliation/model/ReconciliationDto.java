package com.inventory.inventoryservice.reconciliation.model;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationDto {

    private LocalDate reconciliationDate;
    private List<ReconciliationItemDto> items;

}
