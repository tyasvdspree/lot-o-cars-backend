package nl.lotocars.rental.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.lotocars.rental.services.CarImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
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
