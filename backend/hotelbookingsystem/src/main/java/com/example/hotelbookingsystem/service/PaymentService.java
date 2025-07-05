package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Payment;
import com.example.hotelbookingsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentByBooking(Booking booking) {
        return paymentRepository.findByBooking(booking);
    }
}
