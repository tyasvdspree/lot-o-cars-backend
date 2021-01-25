package nl.lotocars.rental.repositories;

import nl.lotocars.rental.Errors.CarNotFoundException;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.reposetories.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CarRepositoryTest {
    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        Car car = new Car();
        car.setNumberPlate("H-735-GT");
        Mockito.when(carRepository.findByNumberPlate(car.getNumberPlate()))
                .thenReturn(java.util.Optional.of(car));
    }

    @AfterEach
    void tearDown() {
        List<Car> cars = carRepository.findAll();
        cars.forEach(car -> carRepository.delete(car));
    }

    @Test
    void findByNumberPlate() {
        String numberPlate = "H-735-GT";

        Car foundCar = null;

        Optional<Car> car = carRepository.findByNumberPlate(numberPlate);
        if(car.isPresent()){
            foundCar = car.get();
        }


        assertNotNull(foundCar);
    }

    @Test
    void findByNumberPlateNotFound() {
        String numberPlate = "H-735-BE";
        Car foundCar = null;

        Optional<Car> car = carRepository.findByNumberPlate(numberPlate);
        if(car.isPresent()){
            foundCar = car.get();
        }

        assertNull(foundCar);
    }

    @Test
    void findBySearchOptions() {

    }

    @Test
    void findByUser() {

    }
}
