package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.dto.BookingRequest;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.RoomType;
import com.example.hotelbookingsystem.model.User;
import com.example.hotelbookingsystem.repository.BookingRepository;
import com.example.hotelbookingsystem.repository.RoomTypeRepository;
import com.example.hotelbookingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          RoomTypeRepository roomTypeRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<Booking> getBookingsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return List.of();
        return bookingRepository.findByUserId(user.getId());
    }

    public Booking saveBooking(BookingRequest request) {
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (request.getRoomTypeId() == null) {
            throw new IllegalArgumentException("Room Type ID must not be null");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Room type not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoomType(roomType);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setNumRooms(request.getNumRooms());
        booking.setGuestName(request.getGuestName());
        booking.setBookingStatus("booked");

        return bookingRepository.save(booking);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // ✅ New method for admin.html to get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
