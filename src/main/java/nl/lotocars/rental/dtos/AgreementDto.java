package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;

import java.util.Date;

@Getter
@Setter
public class AgreementDto {

    private Long id;
    private Car car;
    private User renter;
    private User rentee;
    private Date startDate;
    private Date endDate;
    private double rentPricePerHour;
    private double brokerFee;
    private boolean isPayed;
}
