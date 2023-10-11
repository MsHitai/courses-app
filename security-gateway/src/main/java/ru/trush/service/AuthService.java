package ru.trush.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.trush.config.JwtGenerator;
import ru.trush.dto.JwtRequest;
import ru.trush.dto.JwtResponse;
import ru.trush.dto.RegistrationDto;
import ru.trush.dto.UserDto;
import ru.trush.exception.AuthenticationException;
import ru.trush.model.User;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDetailsImpl userService;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<Object> createAuthToken(@RequestBody JwtRequest authRequest) throws NoSuchAlgorithmException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неправильный логин или пароль");
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtGenerator.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<Object> createNewUser(@RequestBody RegistrationDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new AuthenticationException("Пароли не совпадают");
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            throw new AuthenticationException("Пользователь с указанным именем уже существует");
        }
        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }
}
