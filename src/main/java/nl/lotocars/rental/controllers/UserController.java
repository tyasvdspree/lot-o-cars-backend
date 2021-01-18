package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.exceptions.UserNotFoundException;
import nl.lotocars.rental.dtos.UserDto;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.entities.UserPrincipal;
import nl.lotocars.rental.mapper.UserMapper;
import nl.lotocars.rental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<UserDto>> getUsers(){
        Collection<User> cars = userService.getUsers();
        Collection<UserDto> mappedCars = cars.parallelStream()
                .map(userMapper::mapToDestination).collect(Collectors.toList());
        return new ResponseEntity<>(mappedCars, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUser(@PathVariable long userId){
        Optional<User> user = userService.getUser(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }

        UserDto mappedUser = userMapper.mapToDestination(user.get());
        return new ResponseEntity<>(mappedUser, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto inputUserDto){
        userService.registerUser(userMapper.mapToSource(inputUserDto));
        return new ResponseEntity<>(inputUserDto, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getProfile(@AuthenticationPrincipal UserPrincipal rentee){
        UserPrincipal user = (UserPrincipal) userService.loadUserByUsername(rentee.getUsername());
        User loggedInUser = new User();
        loggedInUser.setId(user.getUserId());
        loggedInUser.setUsername(user.getUsername());
        loggedInUser.setFirstname(user.getFirstName());
        loggedInUser.setLastname(user.getLastName());
        loggedInUser.setPhonenumber(user.getPhoneNumber());
        loggedInUser.setEmailaddress(user.getEmailAddress());
        loggedInUser.setPassword("******");
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }

    @PutMapping("/editme")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> editUser(@AuthenticationPrincipal UserPrincipal loggedUser, @RequestBody User inputUser){
        UserPrincipal user = (UserPrincipal) userService.loadUserByUsername(loggedUser.getUsername());
        User userToChange = user.getUser();

        userToChange.setFirstname((inputUser.getFirstname() != null && !inputUser.getFirstname().isEmpty()) ? inputUser.getFirstname() : user.getFirstName());
        userToChange.setLastname((inputUser.getLastname() != null && !inputUser.getLastname().isEmpty()) ? inputUser.getLastname() : user.getLastName());
        userToChange.setPhonenumber((inputUser.getPhonenumber() != null && !inputUser.getPhonenumber().isEmpty()) ? inputUser.getPhonenumber() : user.getPhoneNumber());
        userToChange.setEmailaddress((inputUser.getEmailaddress() != null && !inputUser.getEmailaddress().isEmpty()) ? inputUser.getEmailaddress() : user.getEmailAddress());
        userToChange.setPassword((inputUser.getPassword() != null && !inputUser.getPassword().isEmpty()) ? inputUser.getPassword() : user.getPassword());

        userService.editUser(userToChange);
        return new ResponseEntity<>(userToChange, HttpStatus.OK);
    }

    @GetMapping("/checkUserEmailAddress")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> checkIfUserEmailAddressExists(@RequestParam("userId") String userId, @RequestParam("userEmailAddress") String userEmailAddress){
        var usernameDoesExist = userService.checkIfUserEmailAddressExists(userId, userEmailAddress);
        return new ResponseEntity<>(usernameDoesExist, HttpStatus.OK);
    }

    @GetMapping("/checkUsername")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> checkIfUsernameExists(@RequestParam("username") String username){
        var usernameDoesExist = userService.checkIfUsernameExists(username);
        return new ResponseEntity<>(usernameDoesExist, HttpStatus.OK);
    }

    @GetMapping("checkIfEmailAddressExistsAtRegistration")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> checkIfEmailAddressExistsAtRegistration(@RequestParam("userEmailAddress") String userEmailAddress){
        var usernameDoesExist = userService.checkIfEmailAddressExistsAtRegistration(userEmailAddress);
        return new ResponseEntity<>(usernameDoesExist, HttpStatus.OK);
    }
}
