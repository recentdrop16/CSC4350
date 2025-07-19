package com.example.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelbookingsystem.model.RoomList;

@Repository
    public interface RoomListRepository extends JpaRepository<RoomList, Integer> {
}
