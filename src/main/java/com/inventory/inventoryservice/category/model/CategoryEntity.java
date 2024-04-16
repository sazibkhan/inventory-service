package com.inventory.inventoryservice.category.model;

import com.inventory.inventoryservice.model.Auditable;
import com.inventory.inventoryservice.product.model.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class CategoryEntity extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductEntity> productList;
}
