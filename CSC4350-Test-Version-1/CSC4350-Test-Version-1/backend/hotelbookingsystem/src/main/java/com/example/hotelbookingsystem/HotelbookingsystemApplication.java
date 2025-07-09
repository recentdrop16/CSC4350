package com.example.hotelbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@SpringBootApplication
public class HotelbookingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelbookingsystemApplication.class, args);
        System.out.println("✅ Hotel Booking System has started successfully!");
    }

    // ✅ Inline CommandLineRunner as a static inner class
    @Component
    public static class AppStartupInitializer implements CommandLineRunner {
        @Override
        public void run(String... args) {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            System.out.println("🌐 Default timezone set to UTC.");
        }
    }
}
