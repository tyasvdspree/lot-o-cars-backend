package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private int modelYear;
    @NotEmpty
    private Make.make make;
    @NotEmpty
    private String model;
    @NotEmpty
    private CarBody.carBody body;
    @NotEmpty
    private Color.color color;
    @NotEmpty
    private Transmission.transmission transmission;
    @NotEmpty
    private Fuel.fuel fuel;
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
    @NotEmpty
    private double rentPricePerHour;
    private boolean navigation;
    private boolean airco;
    private boolean smokingIsAllowed;
    private boolean isActive;

}
