package nl.lotocars.rental.services;

import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.CarImageRepository;
import nl.lotocars.rental.reposetories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CarImageRepository.class, CarImageService.class})
public class CarImageServiceTest {
    @Mock
    CarImageRepository carImageRepository;

    @InjectMocks
    CarImageService carImageService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCarImageById() {
    }

    @Test
    void addImageFile() {
    }
    @Test
    void deleteImage() {
    }
    @Test
    void getImageIds() {
    }
}
