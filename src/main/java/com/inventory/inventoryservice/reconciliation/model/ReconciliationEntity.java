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

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "enabled")
    private Boolean enabled;


}
