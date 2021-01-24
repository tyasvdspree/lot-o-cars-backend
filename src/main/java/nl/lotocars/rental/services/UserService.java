package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.Location;
import nl.lotocars.rental.entities.Role;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.exceptions.UserNotFoundException;
import nl.lotocars.rental.reposetories.LocationRepository;
import nl.lotocars.rental.reposetories.RoleRepository;
import nl.lotocars.rental.reposetories.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private static int workload = 10;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final RoleRepository roleRepository;

    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId){
        return Optional.ofNullable(userRepository.getOne(userId));
    }


    @Transactional(readOnly = false)
    public Optional<User> registerUser(User user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(hashPassword(user.getPassword()));
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setPhonenumber(user.getPhonenumber());
        newUser.setEmailaddress(user.getEmailaddress());
        newUser.setBrokerFee(5);
        newUser.setActive(true);
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("model", ignoreCase());
        Example<Location> example = Example.of(user.getLocation(), modelMatcher);
        locationRepository.findOne(example).ifPresentOrElse(
                newUser::setLocation,
                () -> newUser.setLocation(locationRepository.save(user.getLocation())));
        return Optional.ofNullable(userRepository.save(newUser));
    }

    @Transactional(readOnly = false)
    public User editUser(User user){
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

    public Boolean checkIfUserEmailAddressExists(String userId, String userEmailAddress){
        var userById = userRepository.findUserByUserId(Long.parseLong(userId));
        var userByEmailAddress = userRepository.findUserByUserEmailAddress(userEmailAddress);

        if (userByEmailAddress == null){
            return false;
        }
        else{
            if (userById.getEmailaddress() == userByEmailAddress.getEmailaddress()){
                return false;
            }
            else{
                return true;
            }
        }
    }

    @Transactional(readOnly = false)
    public Boolean checkIfUsernameExists(String username){
        var userByUsername = userRepository.findByUsername(username);
        if (userByUsername.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkIfEmailAddressExistsAtRegistration(String userEmailAddress){
        var userByEmailAddress = userRepository.findUserByUserEmailAddress(userEmailAddress);
        if (userByEmailAddress == null){
            return false;
        }
        else{
            return true;
        }
    }


    @Transactional(readOnly = false)
    public void addRole(long userId, long roleId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findById(roleId).orElseThrow(UserNotFoundException::new);

        user.addRole(role);
        userRepository.save(user);
    }

    @Transactional(readOnly = false)
    public void removeRole(long userId, long roleId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findById(roleId).orElseThrow(UserNotFoundException::new);

        user.removeRole(role);
        userRepository.save(user);
    }
}
