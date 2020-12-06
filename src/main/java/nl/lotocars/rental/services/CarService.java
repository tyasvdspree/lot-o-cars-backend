package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.reposetories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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

    public Collection<Car> searchCars(String city, String make, String color) {
        if (city == "") city = null;
        if (make == "") make = null;
        if (color == "") color = null;
        return carRepository.findBySearchOptions(city, make, color);
    }
}
