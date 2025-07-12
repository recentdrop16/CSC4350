package com.example.hotelbookingsystem.dto;
import com.example.hotelbookingsystem.model.RoomType;
public class AvailableRoomDTO {
    private String roomType;
    private double price;
    private int capacity;
    private String description;
    private double discount;
    private String imageUrl;
    private int availableCount;

    public AvailableRoomDTO(RoomType rt, int availableCount) {
        this.roomType = rt.getRoomType();
        this.price = rt.getPrice();
        this.capacity = rt.getCapacity();
        this.description = rt.getDescription();
        this.discount = rt.getDiscount();
        this.imageUrl = rt.getImageUrl();
        this.availableCount = availableCount;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    // Getters
}
