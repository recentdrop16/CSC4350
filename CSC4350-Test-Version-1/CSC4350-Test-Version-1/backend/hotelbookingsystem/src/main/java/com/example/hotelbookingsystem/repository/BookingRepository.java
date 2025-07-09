package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);

    @Query("SELECT b.room.id FROM Booking b WHERE " +
           "b.checkIn < :checkOut AND b.checkOut > :checkIn")
    List<Long> findBookedRoomIdsInRange(@Param("checkIn") LocalDate checkIn,
                                        @Param("checkOut") LocalDate checkOut);

    List<Booking> findByUserAndIsCancelledFalse(User user);

    @Query("SELECT b FROM Booking b WHERE b.checkIn >= :today AND b.isCancelled = false")
    List<Booking> findAllUpcomingBookings(@Param("today") LocalDate today);
}
