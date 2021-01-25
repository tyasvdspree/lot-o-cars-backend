package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.*;
import nl.lotocars.rental.entities.Location;
import nl.lotocars.rental.entities.User;

@Getter
@Setter
public class CarDto {

    private Long id;
    private User user;
    private Location Location;

    private String numberPlate;
    private int countryCode;
    private int modelYear;
    private Make.make make;
    private String model;
    private CarBody.carBody body;
    private Color.color color;
    private Transmission.transmission transmission;
    private Fuel.fuel fuel;
    private double fuelUsage;
    private int doors;
    private int seats;
    private long bootSpaceInLiters;
    private double rentPricePerHour;
    private boolean navigation;
    private boolean airco;
    private boolean smokingIsAllowed;
    private boolean isActive;
    private Long mainCarImageId;
}
