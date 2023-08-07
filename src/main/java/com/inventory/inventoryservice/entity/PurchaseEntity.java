package com.inventory.inventoryservice.entity;

import com.inventory.inventoryservice.supplier.model.SupplierEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@Entity
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="purchase_date")
    private LocalDateTime purchaseDate;

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
    private boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private  Long updatedBy;
}
