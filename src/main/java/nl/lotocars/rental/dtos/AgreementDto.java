package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.AgreementStatus;

import java.util.Date;

@Getter
@Setter
public class AgreementDto {

    private long id;

    private CarDto car;

    private UserDto renter;

    private UserDto rentee;

    private Date startDate;

    private Date endDate;

    private double rentPricePerHour;

    private double brokerFee;

    private boolean isPayed;

    private AgreementStatus.agreemtStatus status;

    private String reason;
}
