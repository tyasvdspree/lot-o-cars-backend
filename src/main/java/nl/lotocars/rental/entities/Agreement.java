package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Agreement extends BaseEntity {

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "renter_id")
    private User renter;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rentee_id")
    private User rentee;

    private Date startDate;

    private Date endDate;

    private double rentPricePerHour;

    private double brokerFee;

    private boolean isPayed;
}
