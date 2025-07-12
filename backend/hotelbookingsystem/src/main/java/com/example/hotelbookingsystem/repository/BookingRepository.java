package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT COALESCE(SUM(b.numRooms), 0)
        FROM Booking b
        WHERE b.roomType.id = :roomTypeId
        AND b.bookingStatus = 'booked'
        AND NOT (
            b.endDate <= :checkIn OR
            b.startDate >= :checkOut
        )
    """)
    int countBookedRooms(@Param("roomTypeId") Long roomTypeId,
                        @Param("checkIn") LocalDate checkIn,
                        @Param("checkOut") LocalDate checkOut);
    List<Booking> findByUserId(Long userId);
}
