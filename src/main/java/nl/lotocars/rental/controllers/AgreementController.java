package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.exceptions.AgreementNotFoundException;
import nl.lotocars.rental.dtos.*;
import nl.lotocars.rental.dtos.AgreementDto;
import nl.lotocars.rental.dtos.AgreementStatusDto;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.mapper.AgreementCalculatedMapper;
import nl.lotocars.rental.mapper.AgreementMapper;
import nl.lotocars.rental.services.AgreementService;
import nl.lotocars.rental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("agreement")
public class AgreementController {

    private final UserService userService;
    private final AgreementService agreementService;
    private final AgreementMapper agreementMapper;
    private final AgreementCalculatedMapper agreementCalculatedMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<AgreementDto>> getAgreements(@RequestParam(required = false) boolean renter,
                                                                  @AuthenticationPrincipal UserPrincipal userPrincipal){
        Collection<Agreement> agreements = agreementService.findAgreements(userPrincipal, renter);
        Collection<AgreementDto> mappedAgreements = agreements.parallelStream()
                .map(agreementMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<Collection<AgreementDto>>(mappedAgreements, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDto> getAgreement(@PathVariable Long id){
        Optional<Agreement> agreement = agreementService.findById(id);
        if(!agreement.isPresent()){
            throw new AgreementNotFoundException();
        }
        AgreementDto agreementDto = agreementMapper.mapToDestination(agreement.get());
        return new ResponseEntity<AgreementDto>(agreementDto, HttpStatus.OK);
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

    @GetMapping("/rentee_years/{userName}/{startYear}/{endYear}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<AgreementCalculatedDto>> getAgreementsOfRenteeAndYears(
            @PathVariable String userName,
            @PathVariable Integer startYear,
            @PathVariable Integer endYear
    ){
        UserPrincipal userPrincipal =
                (UserPrincipal) userService.loadUserByUsername(userName);
        User user = userPrincipal.getUser();

        Collection<Agreement> agreements =
                agreementService.findByRenteeAndYears(user, startYear, endYear);

        Collection<AgreementCalculatedDto> mappedAgreements = agreements.parallelStream()
                .map(agreementCalculatedMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<>(mappedAgreements, HttpStatus.OK);
    }

    @GetMapping("/brokerfee_totals/{startYear}/{endYear}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<BrokerFeeTotalDto>> getBrokerFeeTotalsOfYears(
            @PathVariable Integer startYear,
            @PathVariable Integer endYear
    ){
        Collection<BrokerFeeTotalDto> totals =
                agreementService.getBrokerFeeTotals(startYear, endYear);

        return new ResponseEntity<>(totals, HttpStatus.OK);
    }

    @GetMapping("/general_counts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<KeyValueDto>> getBrokerFeeTotalsOfYears(){
        Collection<KeyValueDto> counts = agreementService.getGeneralCounts();
        return new ResponseEntity<>(counts, HttpStatus.OK);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDto> addAgreement(
            @RequestBody AgreementDto agreementDto,
            @AuthenticationPrincipal UserPrincipal rentee){
        Agreement agreement = agreementService.createAgreement(agreementMapper.mapToSource(agreementDto), rentee);
        return new ResponseEntity<>(agreementMapper.mapToDestination(agreement), HttpStatus.OK);
    }

    @PutMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDto> cancelAgreement(
            @RequestBody AgreementStatusDto newStatus
    ) {
        Optional<Agreement> agreement = agreementService.setStatus(
            newStatus.getId(),
            newStatus.getStatus(),
            newStatus.getReason()
        );

        if(!agreement.isPresent()){
            throw new AgreementNotFoundException();
        }

        return new ResponseEntity<>(agreementMapper.mapToDestination(agreement.get()), HttpStatus.OK);
    }

    @PutMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDto> payAgreement(@RequestBody AgreementDto agreementDto){
        Agreement agreement = agreementService.setPayment(agreementMapper.mapToSource(agreementDto).getId());
        return new ResponseEntity<>(agreementMapper.mapToDestination(agreement), HttpStatus.OK);
    }
}
