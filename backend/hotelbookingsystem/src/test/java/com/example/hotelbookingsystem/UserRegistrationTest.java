package com.example.hotelbookingsystem;

import com.example.hotelbookingsystem.controller.UserController;
import com.example.hotelbookingsystem.model.User;
import com.example.hotelbookingsystem.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserRegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void newUserCanRegisterSuccessfully() throws Exception {
        
        User newUser = new User();
        newUser.setUsername("uniqueuser");
        newUser.setPassword("securepass");
        newUser.setEmail("unique@example.com");
        newUser.setRole("guest");

        
        when(userService.registerUser(newUser)).thenReturn(newUser);

        
        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk());
    }
}
