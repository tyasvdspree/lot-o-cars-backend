package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Enum.AgreementStatus;
import nl.lotocars.rental.entities.BrokerFeeRequest;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.reposetories.BrokerfeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BrokerFeeService {
    private final BrokerfeeRepository brokerfeeRepository;
    private final UserService userService;

    public Collection<BrokerFeeRequest> getAll(){
        Collection<BrokerFeeRequest> brokerFeeRequests = brokerfeeRepository.findAll();
        return brokerFeeRequests;
    }

    public Optional<BrokerFeeRequest> getOne(Long id){
        Optional<BrokerFeeRequest> brokerFeeRequest = brokerfeeRepository.findById(id);

        return brokerFeeRequest;
    }

    @Transactional(readOnly = false)
    public BrokerFeeRequest createBrokerFeeRequest(BrokerFeeRequest request, UserPrincipal loggedInUser){
        UserPrincipal user = (UserPrincipal) userService.loadUserByUsername(loggedInUser.getUsername());
        request.setUser(user.getUser());
        return brokerfeeRepository.save(request);
    }

    @Transactional(readOnly = false)
    public Optional<BrokerFeeRequest> setStatus(Long id, AgreementStatus.agreemtStatus status, String reason){
        Optional<BrokerFeeRequest> brokerFeeRequest = brokerfeeRepository.findById(id);

        if (brokerFeeRequest.isPresent()) {
            BrokerFeeRequest request = brokerFeeRequest.get();
            if (request.getStatus() != status) {
                request.setStatus(status);
                request.setReason(reason);
                brokerfeeRepository.save(request);
            }
        }

        return brokerFeeRequest;
    }
}
