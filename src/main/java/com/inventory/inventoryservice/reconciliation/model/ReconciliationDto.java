package com.inventory.inventoryservice.reconciliation.model;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationDto {

    private LocalDate reconciliationDate;
    private String  status;
    private boolean enabled;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private  Long updatedBy;
}
