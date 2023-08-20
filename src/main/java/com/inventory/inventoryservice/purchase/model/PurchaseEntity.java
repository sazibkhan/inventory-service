package com.inventory.inventoryservice.purchase.model;

import com.inventory.inventoryservice.supplier.model.SupplierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="purchase_date")
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(
            name = "supplier_id",
            foreignKey = @ForeignKey(
                    name = "purchases_supplier_id_fk"
            ))
    private SupplierEntity supplier;

    @Column(name = "supplier_id", insertable = false, updatable = false)
    private Long supplierId;

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
