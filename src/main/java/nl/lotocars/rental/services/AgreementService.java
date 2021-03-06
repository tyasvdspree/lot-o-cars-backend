package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Enum.AgreementStatus;
import nl.lotocars.rental.exceptions.CarNotFoundException;
import nl.lotocars.rental.exceptions.AgreementNotFoundException;
import nl.lotocars.rental.dtos.BrokerFeeTotalDto;
import nl.lotocars.rental.dtos.KeyValueDto;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.reposetories.AgreementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepository agreementRepository;
    private final CarService carService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Collection<Agreement> findBySearchOptions(
            String numberPlate,
            LocalDate fromDate
    ) {
        return agreementRepository.findBySearchOptions(
                numberPlate == "" ? null : numberPlate,
                java.util.Date.from(fromDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );
    }

    public Agreement createAgreement(Agreement agreement, UserPrincipal loggedInUser){
        Car car = carService.getCarById(agreement.getCar().getId()).orElseThrow(() -> new CarNotFoundException());
        UserPrincipal renter = (UserPrincipal) userService.loadUserByUsername(loggedInUser.getUsername());
        agreement.setBrokerFee(car.getUser().getBrokerFee());
        agreement.setRentPricePerHour(car.getRentPricePerHour());
        agreement.setRenter(renter.getUser());
        agreement.setRentee(car.getUser());
        agreement.setCar(car);
        agreement.setStatus(AgreementStatus.agreemtStatus.PENDING);
        return agreementRepository.save(agreement);
    }

    public Collection<Agreement> findAgreements(UserPrincipal loggedInUser, boolean renterPerspective){
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(loggedInUser.getUsername());
        User user = userPrincipal.getUser();
        if (renterPerspective){
            return agreementRepository.findByRenter(user);
        }
        return agreementRepository.findByRentee(user);
    }

    public Optional<Agreement> findById(Long id){
        return agreementRepository.findById(id);
    }

    public Optional<Agreement> setStatus(Long id, AgreementStatus.agreemtStatus status, String reason){
        Optional<Agreement> agreement = agreementRepository.findById(id);

        if (agreement.isPresent()) {
            Agreement a = agreement.get();
            if (a.getStatus() != status) {
                a.setStatus(status);
                a.setReason(reason);
                agreementRepository.save(a);
            }
        }

        return agreement;
    }

    public Collection<Agreement> findByRenteeAndYears(
            User user,
            Integer startYear,
            Integer endYear
    ){
        return agreementRepository.findByRenteeAndYears(user, startYear, endYear);
    }

    public Collection<BrokerFeeTotalDto> getBrokerFeeTotals(
            Integer startYear,
            Integer endYear
    ){
        return agreementRepository.getBrokerFeeTotals(startYear, endYear);
    }

    public Collection<KeyValueDto> getGeneralCounts() {
        Collection<KeyValueDto> result = new ArrayList<KeyValueDto>();
        result.add(agreementRepository.getGeneralRenteeCount());
        result.add(agreementRepository.getGeneralRenterCount());
        result.add(reduceToKeyValuePair(
                agreementRepository.getGeneralAverageCancellationsPerYear(),
                "avg cancellations per year"));
        result.add(reduceToKeyValuePair(
                agreementRepository.getGeneralAverageInvolvedCarsPerYear(),
                "avg involved cars per year"));
        result.add(reduceToKeyValuePair(
                agreementRepository.getGeneralAverageAgreementsPerYear(),
                "avg agreements per year"));
        return result;
    }

    private KeyValueDto reduceToKeyValuePair(Collection<KeyValueDto> pairs, String label) {
        List<String> values = pairs.stream().map(KeyValueDto::getValue).collect(Collectors.toList());
        double sum = values.stream().mapToDouble(ds -> Double.parseDouble(ds)).sum();
        double avg = sum / pairs.size();
        return new KeyValueDto(label, Double.toString(avg));
    }

    public Agreement setPayment(long id){
        Agreement agreement = agreementRepository.getOne(id);
        agreement.setPayed(true);
        return agreementRepository.save(agreement);
    }

}
