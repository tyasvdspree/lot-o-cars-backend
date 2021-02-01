package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.dtos.BrokerFeeRequestDto;
import nl.lotocars.rental.dtos.BrokerFeeStatusDto;
import nl.lotocars.rental.entities.BrokerFeeRequest;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.exceptions.AgreementNotFoundException;
import nl.lotocars.rental.mapper.BrokerFeeMapper;
import nl.lotocars.rental.services.BrokerFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/brokerfee")
public class BrokerFeeController {

    private final BrokerFeeService brokerFeeService;
    private final BrokerFeeMapper brokerFeeMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<BrokerFeeRequestDto>> getBrokerfeeRequests(){
        Collection<BrokerFeeRequest> brokerFeeRequests = brokerFeeService.getAll();
        Collection<BrokerFeeRequestDto> mappedBrokerFeeRequests = brokerFeeRequests.parallelStream()
                .map(brokerFeeMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<>(mappedBrokerFeeRequests, HttpStatus.OK);
    }

    @GetMapping("/myBrokerfeeRequests")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<BrokerFeeRequestDto>> getMyBrokerfeeRequests(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Collection<BrokerFeeRequest> agreements = brokerFeeService.findBrokerfeeRequests(userPrincipal);
        Collection<BrokerFeeRequestDto> mappedBrokerfeeRequests = agreements.parallelStream()
                .map(brokerFeeMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<Collection<BrokerFeeRequestDto>>(mappedBrokerfeeRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BrokerFeeRequestDto> getBrokerfeeRequest(@PathVariable Long id){
        Optional<BrokerFeeRequest> brokerFeeRequest = brokerFeeService.getOne(id);
        if(!brokerFeeRequest.isPresent()){
            throw new AgreementNotFoundException();
        }
        BrokerFeeRequestDto brokerFeeDto = brokerFeeMapper.mapToDestination(brokerFeeRequest.get());
        return new ResponseEntity<>(brokerFeeDto, HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BrokerFeeRequestDto> createBrokerFeeRequest(@RequestBody BrokerFeeRequestDto brokerFeeRequest, @AuthenticationPrincipal UserPrincipal userPrincipal){
        BrokerFeeRequest brokerFeeRequestEnt = brokerFeeMapper.mapToSource(brokerFeeRequest);
        brokerFeeRequestEnt = brokerFeeService.createBrokerFeeRequest(brokerFeeRequestEnt, userPrincipal);

        BrokerFeeRequestDto brokerFeeDto = brokerFeeMapper.mapToDestination(brokerFeeRequestEnt);
        return new ResponseEntity<>(brokerFeeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BrokerFeeRequestDto> setBrokerfeeStatus(@RequestBody BrokerFeeStatusDto newStatus){
        Optional<BrokerFeeRequest> brokerFeeRequest = brokerFeeService.setStatus(
                newStatus.getId(),
                newStatus.getStatus(),
                newStatus.getReason()
        );

        if(!brokerFeeRequest.isPresent()){
            throw new AgreementNotFoundException();
        }

        return new ResponseEntity<>(brokerFeeMapper.mapToDestination(brokerFeeRequest.get()), HttpStatus.OK);
    }
}
