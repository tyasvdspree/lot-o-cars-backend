package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.lotocars.rental.Enum.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
    private final User user;

    public Long getUserId(){
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getFirstName() {
        return user.getFirstname();
    }

    public String getLastName(){
        return user.getLastname();
    }

    public String getPhoneNumber(){
        return user.getPhonenumber();
    }

    public String getEmailAddress(){
        return user.getEmailaddress();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
