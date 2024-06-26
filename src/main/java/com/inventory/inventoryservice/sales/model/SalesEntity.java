package com.inventory.inventoryservice.sales.model;

import com.inventory.inventoryservice.customer.model.CustomerEntity;
import com.inventory.inventoryservice.model.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "sales")
public class SalesEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sales_date")
    private LocalDate salesDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "sales_customer_id_fk"))
    private CustomerEntity customer;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "sales", fetch = FetchType.LAZY)
    private List<SalesItemEntity> items;


}
