package com.example.hotelbookingsystem;

import com.example.hotelbookingsystem.controller.UserController;
import com.example.hotelbookingsystem.model.User;
import com.example.hotelbookingsystem.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class RegisterWithExistingEmailTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void existingEmailShouldReturn200WithUser() throws Exception {
        String existingEmail = "taken@example.com";

        User existingUser = new User();
        existingUser.setEmail(existingEmail);

        when(userService.getUserByEmail(existingEmail)).thenReturn(Optional.of(existingUser));

        mockMvc.perform(get("/api/users/email/" + existingEmail))
                .andExpect(status().isOk());
    }
}
