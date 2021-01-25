package nl.lotocars.rental.services;

import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.UserRepository;
import nl.lotocars.rental.security.JwtProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserRepository.class, UserService.class})
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void getUser() {

    }

    @Test
    void registerUser() {
        User user = new User();
        user.setActive(true);
        user.setBrokerFee(5);
        user.setPassword("test");
        user.setUsername("test");
        user.setFirstname("meneer");
        user.setLastname("van test");
        user.setEmailaddress("test@test.nl");
        userService.registerUser(user);
        assertNotNull(user);
    }

    @Test
    void editUser() {

    }

    @Test
    void hashPassword() {
        String password = "test";
        String hashPassword = UserService.hashPassword(password);
        assertNotEquals(password, hashPassword);
    }

    @Test
    void loadUserByUsername() {
        String username = "test";
    }
}
