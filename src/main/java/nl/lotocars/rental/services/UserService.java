package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.reposetories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private static int workload = 10;
    private final UserRepository userRepository;

    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId){
        return Optional.ofNullable(userRepository.getOne(userId));
    }


    @Transactional(readOnly = false)
    public User registerUser(User user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(hashPassword(user.getPassword()));
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setPhonenumber(user.getPhonenumber());
        newUser.setEmailaddress(user.getEmailaddress());
        newUser.setBrokerFee(5);
        newUser.setActive(true);
        return userRepository.save(newUser);
    }

    @Transactional(readOnly = false)
    public User editUser(User user){
        user.setPassword(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return (hashed_password);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        return new UserPrincipal(user);
    }

    @Transactional(readOnly = false)
    public Boolean checkIfUserEmailAddressExists(String userEmailAddress){
        var searchedUsername = userRepository.findByUserEmailAddress(userEmailAddress);
        if (searchedUsername == null){
            return false;
        }
        else{
            return true;
        }
    }
}
