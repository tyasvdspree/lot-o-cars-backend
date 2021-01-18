package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.mapper.CarMapper;
import nl.lotocars.rental.services.CarService;
import nl.lotocars.rental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("car")
public class CarController {

    private final UserService userService;
    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> addCar(@RequestBody Car carInputObj, @AuthenticationPrincipal UserPrincipal user) {
        carService.registerCar(carInputObj, user);
        return new ResponseEntity<>(carInputObj, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<CarDto>> getOwnCars(
            @AuthenticationPrincipal UserPrincipal loggedInUser
    ){
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(loggedInUser.getUsername());
        Collection<Car> cars = carService.getCarByOwner(userPrincipal.getUser());
        Collection<CarDto> mappedCars = cars.parallelStream()
                .map(carMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<>(mappedCars, HttpStatus.OK);
    }

    @PutMapping("/editmycar")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public Car editCar(@RequestBody CarDto carDto){
        Car mappedCar = carMapper.mapToSource(carDto);
        carService.saveCar(mappedCar);
        return mappedCar;
    }
}
