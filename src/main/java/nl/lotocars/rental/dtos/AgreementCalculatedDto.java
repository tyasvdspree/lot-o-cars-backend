package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.AgreementStatus;

import java.util.Date;

@Getter
@Setter
public class AgreementCalculatedDto {

    private long id;

    private double rentPricePerHour;

    private double brokerFee;

    private boolean isPayed;

    private AgreementStatus.agreemtStatus status;


    // calculated and other unmapped properties:

    private String carMake;

    private String carModel;

    private String numberPlate;

    private Integer year;

    private Integer month;

    private Long numOfDays;

    private double totalPrice;

    private double brokerCosts;

    private double profit;

}
