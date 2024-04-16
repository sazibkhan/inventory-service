package com.inventory.inventoryservice.purchase.model;

import com.inventory.inventoryservice.model.Auditable;
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
public class PurchaseEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="purchase_date")
    private LocalDate purchaseDate;

    @ManyToOne (fetch=FetchType.LAZY)
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


}
