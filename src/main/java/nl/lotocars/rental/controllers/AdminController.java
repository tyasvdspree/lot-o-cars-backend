package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Enum.Role;
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

    private final UserService userService;

    @PostMapping("/user/{id}/setRole")
    public ResponseEntity addRole(@PathVariable("id") Long userId, @Valid @RequestBody Role role, BindingResult result) {
        if(SecurityUtil.isAuthenticatedUser(userId)){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if(result.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            userService.setRole(userId, role);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
