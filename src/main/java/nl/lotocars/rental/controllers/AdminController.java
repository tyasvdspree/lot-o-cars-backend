package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.dtos.BrokerFeeRequestDto;
import nl.lotocars.rental.dtos.BrokerFeeStatusDto;
import nl.lotocars.rental.entities.BrokerFeeRequest;
import nl.lotocars.rental.exceptions.AgreementNotFoundException;
import nl.lotocars.rental.exceptions.UserNotFoundException;
import nl.lotocars.rental.mapper.BrokerFeeMapper;
import nl.lotocars.rental.security.SecurityUtil;
import nl.lotocars.rental.services.BrokerFeeService;
import nl.lotocars.rental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Admin")
@RequiredArgsConstructor
public class AdminController {

    private final BrokerFeeService brokerFeeService;
    private final BrokerFeeMapper brokerFeeMapper;
    private final UserService userService;

    @GetMapping("/brokerfee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<BrokerFeeRequestDto>> getBrokerfeeRequests(){
        Collection<BrokerFeeRequest> brokerFeeRequests = brokerFeeService.getAll();
        Collection<BrokerFeeRequestDto> mappedBrokerFeeRequests = brokerFeeRequests.parallelStream()
                .map(brokerFeeMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<>(mappedBrokerFeeRequests, HttpStatus.OK);
    }

    @GetMapping("/brokerfee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BrokerFeeRequestDto> getBrokerfeeRequest(@PathVariable Long id){
        Optional<BrokerFeeRequest> brokerFeeRequest = brokerFeeService.getOne(id);
        if(!brokerFeeRequest.isPresent()){
            throw new AgreementNotFoundException();
        }
        BrokerFeeRequestDto brokerFeeDto = brokerFeeMapper.mapToDestination(brokerFeeRequest.get());
        return new ResponseEntity<>(brokerFeeDto, HttpStatus.OK);
    }

    @PutMapping("/brokerfee/{id}/status")
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

    @PostMapping("/user/{id}/addrole")
    public ResponseEntity addRole(@PathVariable("id") Long userId, @Valid @RequestBody Long roleId, BindingResult result) {
        if(SecurityUtil.isAuthenticatedUser(userId)){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if(result.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            userService.addRole(userId, roleId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("user/{id}/removerole")
    public ResponseEntity removeRole(@PathVariable("id") Long userId, @Valid @RequestBody Long roleId, BindingResult result) {
        if(SecurityUtil.isAuthenticatedUser(userId)) return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        if(result.hasErrors()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        try {
            userService.removeRole(userId, roleId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
