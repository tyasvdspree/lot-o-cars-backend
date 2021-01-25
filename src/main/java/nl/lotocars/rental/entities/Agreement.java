package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.AgreementStatus;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Agreement extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne
    @JoinColumn(name = "renter_id")
    private User renter;

    @OneToOne
    @JoinColumn(name = "rentee_id")
    private User rentee;

    private Date startDate;

    private Date endDate;

    private double rentPricePerHour;

    private double brokerFee;

    private boolean isPayed;

    private AgreementStatus.agreemtStatus status;

    private String reason;
}
