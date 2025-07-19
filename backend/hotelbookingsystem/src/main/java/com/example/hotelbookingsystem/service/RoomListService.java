package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.RoomList;
import com.example.hotelbookingsystem.model.RoomType;
import com.example.hotelbookingsystem.repository.RoomListRepository;
import com.example.hotelbookingsystem.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomListService {

    private final RoomListRepository roomListRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomListService(RoomListRepository roomListRepository, RoomTypeRepository roomTypeRepository) {
        this.roomListRepository = roomListRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomList> getAllRooms() {
        return roomListRepository.findAll();
    }

    public RoomList addRoom(RoomList roomList) {
        RoomType roomType = roomTypeRepository.findById(roomList.getRoomType().getId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

     
        roomList.setRoomType(roomType);

   
        RoomList savedRoom = roomListRepository.save(roomList);

       
        roomType.setTotalRooms(roomType.getTotalRooms() + 1);
        roomTypeRepository.save(roomType);

        return savedRoom;
    }


    public RoomList updateRoom(int roomId, RoomList updatedRoom) {
        return roomListRepository.findById(roomId)
                .map(existing -> {
                    RoomType roomType = roomTypeRepository.findById(updatedRoom.getRoomType().getId())
                            .orElseThrow(() -> new RuntimeException("Room type not found"));
                    existing.setRoomType(roomType);
                    return roomListRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void deleteRoom(int roomId) {
        RoomList room = roomListRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        RoomType roomType = room.getRoomType();

      
        roomListRepository.deleteById(roomId);

      
        if (roomType.getTotalRooms() > 0) {
            roomType.setTotalRooms(roomType.getTotalRooms() - 1);
            roomTypeRepository.save(roomType);
        }
    }

}

