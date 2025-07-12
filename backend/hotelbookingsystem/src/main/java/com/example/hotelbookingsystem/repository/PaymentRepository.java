package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Optional: Add custom query methods if needed, for example:
    // List<Payment> findByBookingId(Long bookingId);
}
