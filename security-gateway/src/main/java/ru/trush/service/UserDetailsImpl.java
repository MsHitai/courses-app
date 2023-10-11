package ru.trush.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.trush.dto.RegistrationDto;
import ru.trush.model.User;

import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public Optional<Object> findByUsername(String username) {
        return Optional.empty();
    }

    public User createNewUser(RegistrationDto registrationUserDto) {
        return null;
    }
}
