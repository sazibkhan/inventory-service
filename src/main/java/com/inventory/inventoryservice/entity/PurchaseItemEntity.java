package com.inventory.inventoryservice.entity;

import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "purchase_items")
public class PurchaseItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "purchase_id",
            foreignKey = @ForeignKey(
                    name = "purchase_items_purchase_id_fk"))
    private PurchaseEntity purchase;

    @Column(name = "purchase_id", insertable = false, updatable = false)
    private Long purchaseId;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(
                    name = "purchase_items_product_id_fk"))
    private ProductEntity product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long   productId;

    @Column(name="purchase_price")
    private Double purchasePrice;

    @Column(name="sales_price")
    private Double  salesPrice;

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
