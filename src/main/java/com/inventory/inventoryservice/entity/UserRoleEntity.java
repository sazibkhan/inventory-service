package com.inventory.inventoryservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "auth_users_role")
@Data
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "auth_users_role_user_id_fk"
            )
    )
    private UserEntity user;

    @NotBlank
    @Size(min = 6, max = 20, message = "Role name must be between 6 to 20")
    @Column(name = "role_name")
    private String roleName;
}
