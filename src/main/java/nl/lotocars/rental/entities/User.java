package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.Role;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity(name = "user")
@Table(name = "USER")
public class User extends BaseEntity {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    private String phonenumber;
    private String emailaddress;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    @NotEmpty
    private double brokerFee;

    @NotEmpty
    private boolean isActive;
}
