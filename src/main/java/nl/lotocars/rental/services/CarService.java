package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Enum.*;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.Location;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.reposetories.CarRepository;
import nl.lotocars.rental.reposetories.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final UserService userService;
    private final LocationRepository locationRepository;

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
            String pickupdate,
            String dropoffdate,
            String make,
            String model,
            String color,
            String transmission,
            String fuel,
            String modelyear,
            String doors,
            String seats,
            String bootspace,
            String nonsmoking
    ) throws ParseException {
        return carRepository.findBySearchOptions(
                city == "" ? null : city,
                pickupdate == "" ? null : new SimpleDateFormat("yyyy-MM-dd").parse(pickupdate),
                dropoffdate == "" ? null : new SimpleDateFormat("yyyy-MM-dd").parse(dropoffdate),
                make == "" ? null : Make.make.valueOf(make),
                model == "" ? null : model,
                color == "" ? null : Color.color.valueOf(color),
                transmission == "" ? null : Transmission.transmission.valueOf(transmission),
                fuel == "" ? null : Fuel.fuel.valueOf(fuel),
                modelyear == "" ? 0 : Integer.parseInt(modelyear),
                doors == "" ? 0 : Integer.parseInt(doors),
                seats == "" ? 0 : Integer.parseInt(seats),
                bootspace == "" ? 0 : Integer.parseInt(bootspace),
                nonsmoking == "" ? -1 : Integer.parseInt(nonsmoking),
                AgreementStatus.agreemtStatus.CANCELED
        );
    }

    @Transactional(readOnly = false)
    public Car registerCar(Car car, UserPrincipal loggedInUser){
        UserPrincipal user = (UserPrincipal) userService.loadUserByUsername(loggedInUser.getUsername());
        car.setUser(user.getUser());
        car.setLocation(locationRepository.findById((long) 1).get());
        carRepository.save(car);
        return car;
    }

    @Transactional(readOnly = false)
    public Car saveCar(Car car){
        return carRepository.save(car);
    }
}
