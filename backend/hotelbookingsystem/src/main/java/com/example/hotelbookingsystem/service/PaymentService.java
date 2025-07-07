package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Payment;
import com.example.hotelbookingsystem.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentByBooking(Booking booking) {
        return paymentRepository.findByBooking(booking);
    }
}
