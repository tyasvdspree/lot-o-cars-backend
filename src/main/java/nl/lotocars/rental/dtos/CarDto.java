package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.Transmission;
import nl.lotocars.rental.entities.Location;
import nl.lotocars.rental.entities.User;

import java.util.Date;

@Getter
@Setter
public class CarDto {

    private Long id;
    private User user;
    private Location Location;

    private String numberPlate;
    private int countryCode;
    private Date modelYear;
    private String make;
    private String model;
    private String body;
    private String color;
    private Transmission.transmission transmission;
    private String fuel;
    private double fuelUsage;
    private int doors;
    private int seats;
    private long bootSpaceInLiters;
    private double rentPricePerHour;
    private boolean navigation;
    private boolean airco;
    private boolean smokingIsAllowed;
    private boolean isActive;
}
