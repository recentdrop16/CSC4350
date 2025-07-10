package com.example.hotelbookingsystem;

import com.example.hotelbookingsystem.controller.BookingController;
import com.example.hotelbookingsystem.model.Booking;
import com.example.hotelbookingsystem.model.User;
import com.example.hotelbookingsystem.service.BookingService;
import com.example.hotelbookingsystem.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class UserBookingHistoryTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private UserService userService;

    @Test
    public void userCanViewBookingHistory() throws Exception {
        String username = "john_doe";
        User user = new User();
        user.setUsername(username);
        Booking booking = new Booking();
        booking.setUser(user);

        when(userService.getUserByUsername(username)).thenReturn(Optional.of(user));

        when(bookingService.getBookingsByUser(user)).thenReturn(List.of(booking));

        mockMvc.perform(get("/api/bookings/user/" + username))
                .andExpect(status().isOk());
    }
}
