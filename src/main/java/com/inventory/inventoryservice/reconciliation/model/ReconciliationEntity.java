package com.inventory.inventoryservice.reconciliation.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reconciliations")
public class ReconciliationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="reconciliation_date")
    private LocalDate reconciliationDate;

    @Column(name="reconciliation_status")
    @Enumerated(EnumType.STRING)
    private ReconciliationStatusEnum reconciliationStatus; // Pending, Approved, Rejected

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private  Long updatedBy;
}
