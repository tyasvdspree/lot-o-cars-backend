package nl.lotocars.rental.controllers;

import lombok.AllArgsConstructor;
import nl.lotocars.rental.dtos.AuthenticationResponseDto;
import nl.lotocars.rental.dtos.LoginRequestDto;
import nl.lotocars.rental.dtos.RefreshTokenRequestDto;
import nl.lotocars.rental.services.AuthService;
import nl.lotocars.rental.services.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponseDto refreshTokens(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return authService.refreshToken(refreshTokenRequestDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequestDto.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}