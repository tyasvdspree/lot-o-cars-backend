package nl.lotocars.rental.repositories;

import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.AgreementRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AgreementRepositoryTest {
    @MockBean
    private AgreementRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findBySearchOptions() {
    }

    @Test
    void findByRenter() {
    }

    @Test
    void findByRenteeAndYears() {
    }

    @Test
    void getBrokerFeeTotals() {
    }


    @Test
    void getGeneralRenteeCount() {
    }

    @Test
    void getGeneralRenterCount() {
    }

    @Test
    void getGeneralAverageCancellationsPerYear() {
    }

    @Test
    void getGeneralAverageAgreementsPerYear() {
    }

    @Test
    void getGeneralAverageInvolvedCarsPerYear() {
    }
}
