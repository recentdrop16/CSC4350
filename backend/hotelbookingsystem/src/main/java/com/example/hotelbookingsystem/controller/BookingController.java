package com.example.hotelbookingsystem.controller;

import com.example.hotelbookingsystem.dto.BookingRequest;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        Booking saved = bookingService.updateBooking(id, updatedBooking);
        return ResponseEntity.ok("Booking ID: " + saved.getId() + " updated successfully.");
    }
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        try {
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.ok("Booking cancelled");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error cancelling booking");
        }
    }
}
