package com.example.hotelbookingsystem.model;

public enum Role {
    ADMIN(1),
    CUSTOMER(2);

    private final int code;

    Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Role fromCode(int code) {
        return switch (code) {
            case 1 -> ADMIN;
            case 2 -> CUSTOMER;
            default -> throw new IllegalArgumentException("Unknown role code: " + code);
        };
    }
}
