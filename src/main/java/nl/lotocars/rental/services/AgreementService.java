package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.reposetories.AgreementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepository agreementRepository;

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

    public Agreement createAgreement(Agreement agreement){
        agreement.setBrokerFee(agreement.getRentee().getBrokerFee());
        agreement.setRentPricePerHour(agreement.getCar().getRentPricePerHour());
        return agreementRepository.save(agreement);
    }
}
