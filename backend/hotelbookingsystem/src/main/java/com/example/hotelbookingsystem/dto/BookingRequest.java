package com.example.hotelbookingsystem.dto;

import java.time.LocalDate;

public class BookingRequest {
    private Long userId;
    private Long roomTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numRooms;
    private String guestName;
    private double amount;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Long roomTypeId) { this.roomTypeId = roomTypeId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public int getNumRooms() { return numRooms; }
    public void setNumRooms(int numRooms) { this.numRooms = numRooms; }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
