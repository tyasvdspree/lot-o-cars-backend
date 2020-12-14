package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.Errors.UserNotFoundException;
import nl.lotocars.rental.dtos.UserDto;
import nl.lotocars.rental.entities.User;
import nl.lotocars.rental.mapper.UserMapper;
import nl.lotocars.rental.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> registerUser(@RequestBody User inputUser){
        User newUser = userService.registerUser(inputUser);
//        UserDto mappedUser = userMapper.mapToDestination(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

}
