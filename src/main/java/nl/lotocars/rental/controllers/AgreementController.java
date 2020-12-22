package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.dtos.AgreementDto;
import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.mapper.AgreementMapper;
import nl.lotocars.rental.services.AgreementService;
import nl.lotocars.rental.services.AuthService;
import nl.lotocars.rental.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("agreement")
public class AgreementController {

    private final AuthService authService;
    private final AgreementService agreementService;
    private final AgreementMapper agreementMapper;
    private final CarService carService;

    @GetMapping("/{numberPlate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<LocalDate>> getCarRentedDates(@PathVariable String numberPlate){
        // load agreements of this month and the future
        LocalDate firstOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
        Collection<Agreement> agreements =
                agreementService.findBySearchOptions(numberPlate, firstOfCurrentMonth);

        // return list with all dates in the agreements
        List<LocalDate> dates = new ArrayList<LocalDate>();

        // gather all individual dates of the agreements
        agreements.forEach(x -> {
            LocalDate startDate = new java.sql.Date(x.getStartDate().getTime()).toLocalDate();
            LocalDate endDate = new java.sql.Date(x.getEndDate().getTime()).toLocalDate();
            dates.addAll(startDate.datesUntil(endDate).collect(Collectors.toList()));
            }
        );

        return new ResponseEntity<Collection<LocalDate>>(dates, HttpStatus.OK);
    }

//    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Agreement> createAgreement(@RequestBody Agreement agreement){
//        return new ResponseEntity<>(agreementService.createAgreement(agreement), HttpStatus.OK);
//
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDto> addAgreement(
            @RequestBody AgreementDto agreementDto,
            Authentication authentication){
        String username = authentication.getName();
        Object details = authentication.getDetails();
//        agreementDto.setCar();
        Agreement agr = agreementMapper.mapToSource(agreementDto);
        Optional<Car> car = carService.getCarById(agreementDto.getCarId());

        Agreement agreement = agreementService.createAgreement(agr);
//        agreement.setCar(car);
        return new ResponseEntity<>(agreementMapper.mapToDestination(agreement), HttpStatus.OK);
    }
}
