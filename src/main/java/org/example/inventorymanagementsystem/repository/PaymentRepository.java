package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository
        extends JpaRepository<Payment, Long> {


    boolean existsByPaymentNumber(String paymentNumber);


    @Query("""
            SELECT p FROM Payment p
            JOIN FETCH p.invoice i
            JOIN FETCH i.salesOrder so
            JOIN FETCH so.customer c
            """)
    List<Payment> findAllWithDetails();


    @Query("""
            SELECT p FROM Payment p
            JOIN FETCH p.invoice i
            JOIN FETCH i.salesOrder so
            JOIN FETCH so.customer c
            WHERE p.id = :id
            """)
    Payment findByIdWithDetails(Long id);

}