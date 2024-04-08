package com.inventory.inventoryservice.stock.model;

import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "stocks")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "stocks_product_id_fk"))
    private ProductEntity product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @Column(name = "current_stock")
    private Double currentStock;

    @Column(name = "enabled")
    private boolean enabled;


}
