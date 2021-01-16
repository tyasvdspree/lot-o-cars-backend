package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.entities.Location;

import java.util.Collection;

@Getter
@Setter
public class UserDto {

    private String username;

    private Location location;

    private Collection<String> roles;

    private String firstname;

    private String lastname;

    private String phonenumber;

    private String emailaddress;

    private String password;

}
