package org.example.inventorymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_adjustment_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdjustmentItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "stock_adjustment_id",
            nullable = false
    )
    private StockAdjustment stockAdjustment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    private Product product;


    @Column(nullable = false)
    private Integer systemQuantity;


    @Column(nullable = false)
    private Integer countedQuantity;


    @Column(nullable = false)
    private Integer difference;

}