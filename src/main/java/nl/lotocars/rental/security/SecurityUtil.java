package nl.lotocars.rental.security;

import nl.lotocars.rental.entities.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static boolean isAuthenticatedUser(long userId){
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserId() == userId;
    }

    public static  boolean hasRole(String role){
        var principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.hasRole(role);
    }
}
