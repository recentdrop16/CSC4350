package com.example.hotelbookingsystem.controller;

import com.example.hotelbookingsystem.model.RoomList;
import com.example.hotelbookingsystem.service.RoomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomlist")
@CrossOrigin(origins = "*")
public class RoomListController {

    private final RoomListService roomListService;

    @Autowired
    public RoomListController(RoomListService roomListService) {
        this.roomListService = roomListService;
    }

    @GetMapping
    public List<RoomList> getAllRooms() {
        return roomListService.getAllRooms();
    }

    @PostMapping
    public RoomList addRoom(@RequestBody RoomList roomList) {
        return roomListService.addRoom(roomList);
    }

    @PutMapping("/{roomId}")
    public RoomList updateRoom(@PathVariable int roomId, @RequestBody RoomList updatedRoom) {
        return roomListService.updateRoom(roomId, updatedRoom);
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable int roomId) {
        roomListService.deleteRoom(roomId);
    }
}

