package org.example.inventorymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_ins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, unique = true)
    private String referenceNo;

    private String remarks;

    @Column(nullable = false)
    private LocalDateTime receivedDate;
}