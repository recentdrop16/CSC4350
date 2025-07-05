package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Payment;
import com.example.hotelbookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBooking(Booking booking);
}
