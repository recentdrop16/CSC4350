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
        LocalDate today = LocalDate.now();

        if (booking.getStartDate().isBefore(today) || booking.getEndDate().isBefore(today)) {
            throw new IllegalArgumentException("Booking dates cannot be in the past.");
        }

        if (booking.getEndDate().isBefore(booking.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        RoomType roomType = roomTypeRepository.findById(booking.getRoomType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Room type not found."));

        List<Booking> overlapping = bookingRepository.findAll().stream()
                .filter(b ->
                        !b.getId().equals(booking.getId()) &&
                        b.getRoomType().getId().equals(roomType.getId()) &&
                        !(booking.getEndDate().isBefore(b.getStartDate()) || booking.getStartDate().isAfter(b.getEndDate()))
                )
                .toList();

        int totalBooked = overlapping.stream().mapToInt(Booking::getNumRooms).sum();
        int available = roomType.getTotalRooms() - totalBooked;

        if (booking.getNumRooms() > available) {
            throw new IllegalArgumentException("Not enough rooms available for the selected date range.");
        }

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

        return saveBooking(existing);
    }
}
