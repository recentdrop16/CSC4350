package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.User;
import com.example.hotelbookingsystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking makeBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
    
}
