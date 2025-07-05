package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailability(int availability);
    List<Room> findByIdNotIn(List<Long> ids);
}
