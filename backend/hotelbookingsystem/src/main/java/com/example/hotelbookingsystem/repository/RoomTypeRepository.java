package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType findByRoomType(String roomType);
}
