package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Errors.CarNotFoundException;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.reposetories.AgreementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepository agreementRepository;
    private final CarService carService;

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

    public Agreement createAgreement(Agreement agreement, UserPrincipal rentee){
        Car car = carService.getCarById(agreement.getCar().getId()).orElseThrow(() -> new CarNotFoundException());
        agreement.setBrokerFee(car.getUser().getBrokerFee());
        agreement.setRentPricePerHour(car.getRentPricePerHour());
        agreement.setRenter(car.getUser());
        agreement.setRentee(rentee.getUser());
        return agreementRepository.save(agreement);
    }
}
