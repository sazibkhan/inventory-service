package com.inventory.inventoryservice.product.model;

import com.inventory.inventoryservice.brand.model.BrandEntity;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import com.inventory.inventoryservice.model.Auditable;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity extends Auditable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "description")
  private String description;

  @Column(name = "bar_code")
  private String barCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "products_brand_id_fk"))
  private BrandEntity brand;

  @Column(name = "brand_id", insertable = false, updatable = false)
  private Long brandId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "products_category_id_fk"))
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

}
