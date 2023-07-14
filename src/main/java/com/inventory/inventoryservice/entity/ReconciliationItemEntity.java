package com.inventory.inventoryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "reconciliation_items")
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(
                    name = "purchase_items_product_fk"))
    private ProductEntity product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long   productId;

    @Column(name="reconciliation_date")
    private String reconciliation_date;

    @Column(name="quantity")
    private Double quantity;


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
