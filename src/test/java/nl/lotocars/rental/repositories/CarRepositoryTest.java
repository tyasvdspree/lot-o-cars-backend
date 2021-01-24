package nl.lotocars.rental.repositories;

import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.CarRepository;
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
public class CarRepositoryTest {
    @MockBean
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByNumberPlate() {
    }

    @Test
    void findBySearchOptions() {
    }

    @Test
    void findByUser() {
    }
}
