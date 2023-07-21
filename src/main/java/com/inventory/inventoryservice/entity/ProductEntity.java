package com.inventory.inventoryservice.entity;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "discription")
    private String discription;

    @Column(name = "barcode")
    private String barCode;

    @ManyToOne
    @JoinColumn(
            name = "brand_id",
            foreignKey = @ForeignKey(
                    name = "products_brand_fk"))
    private BrandEntity brand;

    @Column(name = "brand_id", insertable = false, updatable = false)
    private Long brandId;


    @ManyToOne
    @JoinColumn(
            name = "category_id",
            foreignKey =@ForeignKey(
            name = "products_category_fk"))
    private CategoryEntity category;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @Column(name = "product_images")
    private String productImages;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "sales_price")
    private Double salesPrice;

    @Column(name = "discount_amount")
    private Double discountAmount;

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
