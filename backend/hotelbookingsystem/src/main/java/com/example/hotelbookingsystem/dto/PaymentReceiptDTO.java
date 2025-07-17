package com.example.hotelbookingsystem.dto;

import java.time.LocalDate;

public class PaymentReceiptDTO {
    private Long id;
    private String paymentDate;
    private double amount;
    private String paymentStatus;

    private String guestName;
    private String email;
    private String startDate;
    private String endDate;
    private String roomType;
    private int numRooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public PaymentReceiptDTO() {}

    public PaymentReceiptDTO(Long id, String paymentDate, double amount, String paymentStatus,
                             String guestName, String email, String startDate, String endDate,
                             String roomType, int numRooms) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.guestName = guestName;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomType = roomType;
        this.numRooms = numRooms;
    }
}
