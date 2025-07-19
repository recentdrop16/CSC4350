package com.example.hotelbookingsystem.controller;

import com.example.hotelbookingsystem.model.RoomType;
import com.example.hotelbookingsystem.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomtypes")
@CrossOrigin(origins = "*")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public List<RoomType> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @PostMapping
    public RoomType addRoomType(@RequestBody RoomType roomType) {
        return roomTypeService.addRoomType(roomType);
    }

    @PutMapping("/{id}")
    public RoomType updateRoomType(@PathVariable Long id, @RequestBody RoomType updatedRoomType) {
        return roomTypeService.updateRoomType(id, updatedRoomType);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomType(@PathVariable Long id) {
        roomTypeService.deleteRoomType(id);
    }
}
