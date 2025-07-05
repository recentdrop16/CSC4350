package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.repository.BookingRepository;
import com.example.hotelbookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Long> bookedRoomIds = bookingRepository.findBookedRoomIdsInRange(checkIn, checkOut);
        if (bookedRoomIds == null || bookedRoomIds.isEmpty()) {
            return roomRepository.findByAvailability(1); // all available rooms
        } else {
            return roomRepository.findByIdNotIn(bookedRoomIds);
        }
    }
    
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailability(1);

    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
