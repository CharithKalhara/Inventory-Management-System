package org.example.inventorymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "stocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"product_id", "warehouse_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;


    @Column(nullable = false)
    private Integer quantity = 0;


    @Column(nullable = false)
    private Integer reservedQuantity = 0;


    @Column(nullable = false)
    private Integer availableQuantity = 0;

}