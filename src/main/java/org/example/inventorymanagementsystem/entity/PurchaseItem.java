package org.example.inventorymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.inventorymanagementsystem.entity.Product;
import org.example.inventorymanagementsystem.entity.PurchaseOrder;

import java.math.BigDecimal;

@Entity
@Table(name="purchase_items")
@Getter
@Setter
public class PurchaseItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private PurchaseOrder purchaseOrder;


    @ManyToOne
    private Product product;


    private Integer quantity;


    private BigDecimal purchasePrice;


    private BigDecimal subtotal;

}