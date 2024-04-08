package com.inventory.inventoryservice.reconciliation.model;

import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.purchase.model.PurchaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reconciliation_items")
public class ReconciliationItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "reconciliation_id", foreignKey = @ForeignKey(name = "reconciliation_items_reconciliation_id_fk"))
    private ReconciliationEntity reconciliation;

    @Column(name = "reconciliation_id", insertable = false, updatable = false)
    private Long reconciliationId;

    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "reconciliation_items_product_id_fk"))
    private ProductEntity product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long  productId;

    @Column(name = "reconciliation_type")
    @Enumerated(EnumType.STRING)
    private ReconciliationType reconciliationType;

    @Column(name="quantity")
    private Double quantity;

    @Column(name = "enabled")
    private Boolean enabled;


}
