package com.inventory.inventoryservice.brand.model;

import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "brands")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<ProductEntity> productList;

}
