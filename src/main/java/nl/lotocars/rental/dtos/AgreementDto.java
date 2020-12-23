package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AgreementDto {

    private long id;

    private long carId;

    private long renterId;

    private long renteeId;

    private Date startDate;

    private Date endDate;

    private double rentPricePerHour;

    private double brokerFee;

    private boolean isPayed;
}
