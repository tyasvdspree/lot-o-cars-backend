package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.Transmission;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Entity (name = "Car")
@Table (name = "CAR")
public class Car extends BaseEntity {

    @NotEmpty
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @NotEmpty
    private String numberPlate;

    @NotEmpty
    private int countryCode;
    @NotEmpty
    private Date modelYear;
    @NotEmpty
    private String make;
    @NotEmpty
    private String model;
    @NotEmpty
    private String body;
    @NotEmpty
    private Transmission.transmission transmission;
    @NotEmpty
    private String fuel;
    @NotEmpty
    private double fuelUsage;
    @NotEmpty
    @Min(1)
    private int doors;
    @NotEmpty
    @Min(1)
    private int seats;
    @NotEmpty
    private long bootSpaceInLiters;
    private boolean navigation;
    private boolean airco;
    private boolean smokingIsAllowed;
    private boolean isActive;

}
