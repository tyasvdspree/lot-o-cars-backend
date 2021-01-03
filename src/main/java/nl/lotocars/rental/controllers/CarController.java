package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.mapper.CarMapper;
import nl.lotocars.rental.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("car")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> addCar(@RequestBody Car carInputObj) {
        carService.registerCar(carInputObj);
        return new ResponseEntity<>(carInputObj, HttpStatus.CREATED);
    }
}
