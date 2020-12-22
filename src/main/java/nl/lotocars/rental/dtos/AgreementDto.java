package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;

import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
public class AgreementDto {

    private long id;
//    @OneToOne
    private long carId;
//    @OneToOne
    private long renterId;
//    @OneToOne
    private long renteeId;
    private Date startDate;
    private Date endDate;
//    private double rentPricePerHour;
//    private double brokerFee;
//    private boolean isPayed;
}
