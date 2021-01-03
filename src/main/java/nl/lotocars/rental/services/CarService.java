package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Collection<Car> getCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCar(String numberPlate){
        return carRepository.findByNumberPlate(numberPlate);
    }

    public Optional<Car> getCarById(long id){
        return carRepository.findById(id);
    }

    public Collection<Car> getCarByOwner(User owner) {
        return carRepository.findByUser(owner);
    }

    public Collection<Car> searchCars(
            String city,
            String make,
            String model,
            String color,
            String fuel,
            String modelyear,
            String doors,
            String seats,
            String bootspace,
            String nonsmoking
    ) {
        return carRepository.findBySearchOptions(
                city == "" ? null : city,
                make == "" ? null : make,
                model == "" ? null : model,
                color == "" ? null : color,
                fuel == "" ? null : fuel,
                modelyear == "" ? 0 : Integer.parseInt(modelyear),
                doors == "" ? 0 : Integer.parseInt(doors),
                seats == "" ? 0 : Integer.parseInt(seats),
                bootspace == "" ? 0 : Integer.parseInt(bootspace),
                nonsmoking == "" ? -1 : Integer.parseInt(nonsmoking)
        );
    }
    @Transactional(readOnly = false)
    public Car registerCar(Car car){
        carRepository.save(car);
        return car;

    }

    @Transactional(readOnly = false)
    public Car saveCar(Car car){
        return carRepository.save(car);
    }
}
