package org.example.inventorymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


    @Enumerated(EnumType.STRING)
    private MovementType type;


    private Integer quantity;


    private Integer previousQuantity;


    private Integer newQuantity;


    private String reference;


    private LocalDateTime createdAt;


    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
    }
}