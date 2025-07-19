package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.RoomType;
import com.example.hotelbookingsystem.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public RoomType addRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public RoomType updateRoomType(Long id, RoomType updatedRoomType) {
        return roomTypeRepository.findById(id).map(existing -> {
            existing.setRoomType(updatedRoomType.getRoomType());
            existing.setPrice(updatedRoomType.getPrice());
            existing.setCapacity(updatedRoomType.getCapacity());
            existing.setDescription(updatedRoomType.getDescription());
            existing.setDiscount(updatedRoomType.getDiscount());
            existing.setImageUrl(updatedRoomType.getImageUrl());
            existing.setTotalRooms(updatedRoomType.getTotalRooms());
            return roomTypeRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Room type not found with id: " + id));
    }

    public void deleteRoomType(Long id) {
        roomTypeRepository.deleteById(id);
    }
}
