package com.example.hotelbookingsystem.controller;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.User;
import com.example.hotelbookingsystem.service.BookingService;
import com.example.hotelbookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Booking makeBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking); // updated version
    }


    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/user/{username}")
    public List<Booking> getBookingsByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username).orElse(null);
        return user != null ? bookingService.getBookingsByUser(user) : List.of();
    }
}
