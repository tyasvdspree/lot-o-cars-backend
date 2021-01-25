package nl.lotocars.rental.repositories;

import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUsername("testUserName");
        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findByUsername() {
        String username = "testUserName";
        Optional<User> userFound = userRepository.findByUsername(username);
        assertEquals(userFound.get().getUsername(), username);
    }

    @Test
    void findById() {
    }
}