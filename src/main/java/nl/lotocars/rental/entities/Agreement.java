package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Agreement extends BaseEntity {

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "renter_id")
    private User renter;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "rentee_id")
    private User rentee;

    private Date startDate;

    private Date endDate;

    private double rentPricePerHour;

    private double brokerFee;

    private boolean isPayed;
}
