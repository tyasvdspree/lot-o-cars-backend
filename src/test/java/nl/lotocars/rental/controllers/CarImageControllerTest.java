package nl.lotocars.rental.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.lotocars.rental.configs.WebSecurityConfig;
import nl.lotocars.rental.reposetories.CarImageRepository;
import nl.lotocars.rental.reposetories.UserRepository;
import nl.lotocars.rental.security.JwtAuthenticationFilter;
import nl.lotocars.rental.security.JwtProvider;
import nl.lotocars.rental.services.CarImageService;
import nl.lotocars.rental.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@ContextConfiguration
public class CarImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    CarImageController carImageController;

    @Mock
    CarImageService carImageService;


    @Test
    void getImageIds() throws Exception {
        String numberPlate = "H-735-GT";
        MvcResult res = mockMvc.perform(get("/"+numberPlate)).andReturn();
    }

    @Test
    void getImage() {
    }

    @Test
    void handleImagePost() {
    }

    @Test
    void deleteImage() {
    }
}
