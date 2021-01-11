package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Enum.AgreementStatus;
import nl.lotocars.rental.Errors.CarNotFoundException;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.reposetories.AgreementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Optional;

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
        UserPrincipal rentee = (UserPrincipal) userService.loadUserByUsername(loggedInUser.getUsername());
        agreement.setBrokerFee(car.getUser().getBrokerFee());
        agreement.setRentPricePerHour(car.getRentPricePerHour());
        agreement.setRenter(car.getUser());
        agreement.setRentee(rentee.getUser());
        agreement.setCar(car);
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
}
