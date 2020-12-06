package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Errors.CarNotFoundException;
import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.mapper.CarMapper;
import nl.lotocars.rental.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("renting")
public class CarRentController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<CarDto>> getCars(){
        Collection<Car> cars = carService.getCars();
        Collection<CarDto> mappedCars = cars.parallelStream()
                .map(carMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<>(mappedCars, HttpStatus.OK);
    }

    @GetMapping("/{numberPlate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarDto> getCar(@PathVariable String numberPlate){
        Optional<Car> car = carService.getCar(numberPlate);
        if(!car.isPresent()){
            throw new CarNotFoundException();
        }

        CarDto mappedCar = carMapper.mapToDestination(car.get());
        return new ResponseEntity<>(mappedCar, HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<CarDto>> search(
        @RequestParam("city") String city,
        @RequestParam("make") String make,
        @RequestParam("color") String color
    ){
        Collection<Car> cars = carService.searchCars(city, make, color);
        Collection<CarDto> mappedCars = cars.parallelStream()
                .map(carMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<>(mappedCars, HttpStatus.OK);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto car){
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
/*
    @PostMapping("/{numberPlate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarDto> changeCar(@PathVariable String numberPlate, @RequestBody CarDto car){

    }

    @DeleteMapping("/{numberPlate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarDto> deleteCar(@PathVariable String numberPlate){

    }*/
}
