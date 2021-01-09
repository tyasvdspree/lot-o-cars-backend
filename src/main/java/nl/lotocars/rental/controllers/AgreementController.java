package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.dtos.AgreementDto;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.mapper.AgreementMapper;
import nl.lotocars.rental.services.AgreementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("agreement")
public class AgreementController {

    private final AgreementService agreementService;
    private final AgreementMapper agreementMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<AgreementDto>> getAgreements(@RequestParam(required = false) boolean renter, @AuthenticationPrincipal UserPrincipal userPrincipal){
        Collection<Agreement> agreements = agreementService.findAgreements(userPrincipal, renter);
        Collection<AgreementDto> mappedAgreements = agreements.parallelStream()
                .map(agreementMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<Collection<AgreementDto>>(mappedAgreements, HttpStatus.OK);
    }

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
            LocalDate endDate = new java.sql.Date(x.getEndDate().getTime()).toLocalDate().plusDays(1);
            dates.addAll(startDate.datesUntil(endDate).collect(Collectors.toList()));
            }
        );

        return new ResponseEntity<Collection<LocalDate>>(dates, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDto> addAgreement(
            @RequestBody AgreementDto agreementDto,
            @AuthenticationPrincipal UserPrincipal rentee){
        Agreement agreement = agreementService.createAgreement(agreementMapper.mapToSource(agreementDto), rentee);
        return new ResponseEntity<>(agreementMapper.mapToDestination(agreement), HttpStatus.OK);
    }
}
