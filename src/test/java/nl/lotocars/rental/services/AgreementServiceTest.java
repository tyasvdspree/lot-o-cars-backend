package nl.lotocars.rental.services;

import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.reposetories.AgreementRepository;
import nl.lotocars.rental.reposetories.UserRepository;
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
@ContextConfiguration(classes = {AgreementRepository.class, AgreementService.class})
public class AgreementServiceTest {
    @Mock
    AgreementRepository agreementRepository;

    @InjectMocks
    AgreementService agreementService;


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
    void createAgreement() {
    }

    @Test
    void findAgreements() {
    }

    @Test
    void findById() {
    }

    @Test
    void setStatus() {
    }

    @Test
    void findByRenteeAndYears() {
    }

    @Test
    void getBrokerFeeTotals() {
    }

    @Test
    void getGeneralCounts() {
    }

    @Test
    void reduceToKeyValuePair() {
    }

    @Test
    void setPayment() {
    }
}
