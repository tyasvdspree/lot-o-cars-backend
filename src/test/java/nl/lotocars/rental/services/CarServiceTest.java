package nl.lotocars.rental.services;

import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.CarRepository;
import nl.lotocars.rental.reposetories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = { CarService.class, CarRepository.class, UserRepository.class}, loader = AnnotationConfigContextLoader.class)
public class CarServiceTest {
    @Mock
    CarRepository carRepository;
    UserRepository userRepository;

    @InjectMocks
    CarService carService;

    @BeforeEach
    void setUp() {
        Car car = new Car();
        User user = new User();
        user.setUsername("test1");
        car.setNumberPlate("H-735-GT");
        car.setUser(user);
        Collection<Car> cars = new ArrayList<>();
        cars.add(car);
        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));
        Mockito.when(carRepository.findByUser(car.getUser()))
                .thenReturn(cars);
        Mockito.when(carRepository.findByNumberPlate(car.getNumberPlate()))
                .thenReturn(java.util.Optional.of(car));
        Mockito.when(carRepository.getOne((long) 0)).thenReturn(car);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getCars() {
        carService.getCars();
    }

    @Test
    void getCar() {
        String numberPlate = "H-735-GT";
        Car foundCar = null;

        Optional<Car> car = carService.getCar(numberPlate);
        if(car.isPresent()){
            foundCar = car.get();
        }

        assertNotNull(foundCar);
    }

    @Test
    void getCarNotFound(){
        String numberPlate = "H-735-BE";
        Car foundCar = null;

        Optional<Car> car = carService.getCar(numberPlate);
        if(car.isPresent()){
            foundCar = car.get();
        }

        assertNull(foundCar);
    }

    @Test
    void getCarById() {
        long id = 0;

        Optional<Car> foundCar = carService.getCarById(id);

        assertNotNull(foundCar);
    }

    @Test
    void getCarByOwner() {
        User user = userRepository.findByUsername("test1").get();

        Collection<Car> carsByOwner = carService.getCarByOwner(user);

        assertNotEquals(0, carsByOwner.size());
    }

    @Test
    void searchCars() {
    }

    @Test
    void registerCar() {

    }

    @Test
    void saveCar() {

    }
}
