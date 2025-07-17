package com.example.hotelbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.hotelbookingsystem.dto.PaymentReceiptDTO;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Payment;
import com.example.hotelbookingsystem.repository.PaymentRepository;
import com.example.hotelbookingsystem.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @GetMapping("/booking/{bookingId}")
public ResponseEntity<PaymentReceiptDTO> getPaymentDetailsByBookingId(@PathVariable Long bookingId) {
    Optional<Payment> optionalPayment = paymentRepository.findByBookingId(bookingId);
    if (optionalPayment.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Payment payment = optionalPayment.get();

    Optional<Booking> optionalBooking = bookingRepository.findById(payment.getBookingId());
    if (optionalBooking.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }

    Booking booking = optionalBooking.get();

    PaymentReceiptDTO dto = new PaymentReceiptDTO(
        payment.getId(),
        payment.getPaymentDate().toString(),
        payment.getAmount(),
        payment.getPaymentStatus(),
        booking.getGuestName(),
        booking.getUser().getEmail(),
        booking.getStartDate().toString(),
        booking.getEndDate().toString(),
        booking.getRoomType().getRoomType(),
        booking.getNumRooms()
    );

    return ResponseEntity.ok(dto);
}

}
