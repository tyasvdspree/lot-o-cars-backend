package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.dtos.UserDto;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService/* implements UserDetailsService */{

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
        newUser.setPassword(user.getPassword());
        newUser.setBrokerFee(5);
        newUser.setActive(true);
        return userRepository.save(newUser);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        return new UserPrincipal(user);
    }*/
}
