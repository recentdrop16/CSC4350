package com.example.hotelbookingsystem.controller;

import com.example.hotelbookingsystem.dto.BookingRequest;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/user/{username}")
    public List<Booking> getBookingsByUser(@PathVariable String username) {
        return bookingService.getBookingsByUsername(username);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmBooking(@RequestBody BookingRequest request) {
        Booking saved = bookingService.saveBooking(request);
        return ResponseEntity.ok("Booking ID: " + saved.getId() + " saved.");
    }

    // ✅ New endpoint added for admin.html to load all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
