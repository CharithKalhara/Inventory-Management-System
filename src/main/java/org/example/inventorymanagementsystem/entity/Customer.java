package org.example.inventorymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerCode;

    @Column(nullable = false)
    private String name;

    private String email;

    private String phone;

    private String address;

    @Column(nullable = false)
    private Boolean active;
}