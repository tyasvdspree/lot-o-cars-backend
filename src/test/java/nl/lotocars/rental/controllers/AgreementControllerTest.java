package nl.lotocars.rental.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AgreementController.class)
public class AgreementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAgreements() {
    }

    @Test
    void getAgreement() {
    }

    @Test
    void getCarRentedDates() {
    }

    @Test
    void getAgreementsOfRenteeAndYears() {
    }

    @Test
    void getBrokerFeeTotalsOfYears() {
    }

    @Test
    void addAgreement() {
    }

    @Test
    void cancelAgreement() {
    }

    @Test
    void payAgreement() {
    }
}
