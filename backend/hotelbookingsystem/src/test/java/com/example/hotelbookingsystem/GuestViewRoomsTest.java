package com.example.hotelbookingsystem;

import com.example.hotelbookingsystem.controller.RoomController;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class GuestViewRoomsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void guestCanViewAvailableRooms() throws Exception {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(2);

        when(roomService.getAvailableRooms(checkIn, checkOut))
                .thenReturn(List.of(new Room()));

        mockMvc.perform(get("/api/rooms/available")
                .param("checkIn", checkIn.toString())
                .param("checkOut", checkOut.toString()))
                .andExpect(status().isOk());
    }
}
