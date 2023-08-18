package com.inventory.inventoryservice.sales;

import com.inventory.inventoryservice.product.model.ProductEntity;
import com.inventory.inventoryservice.sales.model.SalesEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sales_items")
public class SalesItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id", foreignKey = @ForeignKey(name = "sales_items_sales_id_fk"))
    private SalesEntity sales;

    @Column(name = "sales_id", insertable = false, updatable = false)
    private Long salesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "sales_items_product_id_fk"))
    private ProductEntity product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "sales_price")
    private Double salesPrice;

    @Column(name = "discount_amount")
    private Double discountAmount;

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
