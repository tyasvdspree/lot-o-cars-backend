package nl.lotocars.rental.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.CarRepository;
import nl.lotocars.rental.reposetories.UserRepository;
import nl.lotocars.rental.security.JwtProvider;
import nl.lotocars.rental.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Mock
    JwtProvider jwtProvider;

    @Test
    void getUsers() {
    }

    @Test
    void getUser() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void getProfile() {
    }

    @Test
    void editUser() {
    }
}