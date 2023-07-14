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
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
public class SalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sales_date")
    private LocalDateTime salesDate;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(
                    name = "sales_customer_fk"))
    private CustomerEntity customer;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

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
