package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.dto.AvailableRoomDTO;
import com.example.hotelbookingsystem.model.RoomType;
import com.example.hotelbookingsystem.repository.RoomTypeRepository;
import com.example.hotelbookingsystem.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomTypeRepository roomTypeRepository;
    private final BookingRepository bookingRepository;

    public RoomService(RoomTypeRepository roomTypeRepository,
                       BookingRepository bookingRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<AvailableRoomDTO> getAvailableRoomTypes(LocalDate checkIn, LocalDate checkOut,
                                                        Integer capacity, String type, Integer requestedRoomCount) {

        List<RoomType> allRoomTypes = roomTypeRepository.findAll();
        List<AvailableRoomDTO> result = new ArrayList<>();

        for (RoomType rt : allRoomTypes) {
            if (capacity != null && rt.getCapacity() < capacity) continue;
            if (type != null && !rt.getRoomType().equalsIgnoreCase(type)) continue;

            int totalRooms = rt.getTotalRooms(); // ✅ using column from RoomType table
            int bookedRooms = bookingRepository.countBookedRooms(rt.getId(), checkIn, checkOut);
            int available = totalRooms - bookedRooms;

            if (available > 0 && (requestedRoomCount == null || available >= requestedRoomCount)) {
                result.add(new AvailableRoomDTO(rt, available));
            }
        }

        return result;
    }
}
