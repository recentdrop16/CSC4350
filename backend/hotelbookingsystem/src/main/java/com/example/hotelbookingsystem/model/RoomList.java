package com.example.hotelbookingsystem.model;

import jakarta.persistence.*;
@Entity
@Table(name = "RoomList")
public class RoomList {
    @Id
    @Column(name = "room_id") // manually assigned
    private int roomId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    
}
