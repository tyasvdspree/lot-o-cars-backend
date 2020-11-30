package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;

    private double brokerFee;

    private boolean isActive;

    public void addRole(Role role){
        roles.add(role);
    }
    public void removeRole(Role role){
        roles.remove(role);
    }
}
