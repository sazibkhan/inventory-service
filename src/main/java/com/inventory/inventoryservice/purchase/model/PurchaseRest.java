package com.inventory.inventoryservice.purchase.model;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRest {
    private Long id;
    private LocalDate purchaseDate;
    private String supplier;
    private Long supplierId;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private  Long updatedBy;

}
