package com.inventory.inventoryservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "auth_registration")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;
}
