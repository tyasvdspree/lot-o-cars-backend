package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "location")
@Table(name = "LOCATION")
public class Location extends BaseEntity{
    private String Long;
    private String lat;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipcode;
    private int CountryCode;
    private String Municipality;
}
