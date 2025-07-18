package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.dto.BookingRequest;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.Payment;
import com.example.hotelbookingsystem.model.RoomType;
import com.example.hotelbookingsystem.model.User;
import com.example.hotelbookingsystem.repository.BookingRepository;
import com.example.hotelbookingsystem.repository.PaymentRepository;
import com.example.hotelbookingsystem.repository.RoomTypeRepository;
import com.example.hotelbookingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          RoomTypeRepository roomTypeRepository,
                          PaymentRepository paymentRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.paymentRepository = paymentRepository;
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
        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot book a past date.");
        }
        if (request.getNumRooms() <= 0) {
            throw new IllegalArgumentException("Number of rooms must be at least 1.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Room type not found"));

        int booked = bookingRepository.countBookedRooms(roomType.getId(), request.getStartDate(), request.getEndDate());
        int available = roomType.getTotalRooms() - booked;

        if (request.getNumRooms() > available) {
            throw new IllegalArgumentException("Not enough rooms available.");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoomType(roomType);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setNumRooms(request.getNumRooms());
        booking.setGuestName(request.getGuestName());
        booking.setBookingStatus("booked");

        Booking savedBooking = bookingRepository.save(booking);

        Payment payment = new Payment();
        payment.setBookingId(savedBooking.getId());
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(request.getAmount());
        payment.setPaymentStatus("completed");

        paymentRepository.save(payment);

        return savedBooking;
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public Booking updateBooking(Long bookingId, Booking updatedBooking) {
        Booking existing = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        if (updatedBooking.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot book a past date.");
        }
        if (updatedBooking.getNumRooms() <= 0) {
            throw new IllegalArgumentException("Number of rooms must be at least 1.");
        }

        RoomType roomType = updatedBooking.getRoomType() != null ? 
                            updatedBooking.getRoomType() : existing.getRoomType();

        int booked = bookingRepository.countBookedRooms(roomType.getId(),
                updatedBooking.getStartDate(), updatedBooking.getEndDate());

        // Subtract this booking’s own rooms from total before comparison
        int adjusted = booked - existing.getNumRooms();
        int available = roomType.getTotalRooms() - adjusted;

        if (updatedBooking.getNumRooms() > available) {
            throw new IllegalArgumentException("Not enough rooms available for update.");
        }

        existing.setStartDate(updatedBooking.getStartDate());
        existing.setEndDate(updatedBooking.getEndDate());
        existing.setNumRooms(updatedBooking.getNumRooms());
        existing.setGuestName(updatedBooking.getGuestName());
        existing.setBookingStatus(updatedBooking.getBookingStatus());

        if (updatedBooking.getRoomType() != null) {
            existing.setRoomType(updatedBooking.getRoomType());
        }

        if (updatedBooking.getUser() != null) {
            existing.setUser(updatedBooking.getUser());
        }

        return bookingRepository.save(existing);
    }
}
