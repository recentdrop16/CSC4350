package com.example.hotelbookingsystem.controller;

import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/available")
    public List<Room> getAvailableRooms(
            @RequestParam(required = false) String checkIn,
            @RequestParam(required = false) String checkOut
    ) {
       
            return roomService.getAvailableRooms(LocalDate.parse(checkIn), LocalDate.parse(checkOut));
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
