package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.BrokerFeeRequest;
import nl.lotocars.rental.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BrokerfeeRepository extends JpaRepository<BrokerFeeRequest, Long> {
    Collection<BrokerFeeRequest> findByUser(User renter);
}
