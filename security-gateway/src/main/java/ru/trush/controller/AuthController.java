package ru.trush.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.trush.dto.JwtRequest;
import ru.trush.dto.RegistrationDto;
import ru.trush.service.AuthService;

import java.security.NoSuchAlgorithmException;

@RestController
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@Valid @RequestBody JwtRequest authRequest) throws NoSuchAlgorithmException {
        log.info("Поступил запрос на получения токена от {}", authRequest.getUsername());
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody RegistrationDto registrationUserDto) {
        log.info("Поступил запрос на регистрацию пользователя {}", registrationUserDto.getUsername());
        return authService.createNewUser(registrationUserDto);
    }
}
