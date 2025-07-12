package com.example.hotelbookingsystem.controller;
import java.time.LocalDate;
import com.example.hotelbookingsystem.dto.AvailableRoomDTO;
import com.example.hotelbookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/available")
    public List<AvailableRoomDTO> getAvailableRooms(
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer roomCount
    ) {
        LocalDate start = LocalDate.parse(checkIn);
        LocalDate end = LocalDate.parse(checkOut);
        return roomService.getAvailableRoomTypes(start, end, capacity, type, roomCount);
    }
}
