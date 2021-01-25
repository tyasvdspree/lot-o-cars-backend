package nl.lotocars.rental.repositories;

import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.CarImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class CarImageRepositoryTest {
    @MockBean
    private CarImageRepository carImageRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findIdsByNumberPlate() {
    }

    @Test
    void findImageIdByCarId() {
    }
}
