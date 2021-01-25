package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {
    private String longitude;
    private String latitude;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipcode;
    private int countryCode;
    private String municipality;
}
