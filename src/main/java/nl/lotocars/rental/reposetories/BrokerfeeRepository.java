package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.BrokerFeeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerfeeRepository extends JpaRepository<BrokerFeeRequest, Long> {

}
