package com.inventory.inventoryservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auth_users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 120)
    @Column(name="full_name")
    private String fullName;

    @NotBlank
    private String authority;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            mappedBy = "user"
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    @NotNull
    private Character enabled;
}
