package ru.trush.courses.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.mapper.UserMapper;
import ru.trush.courses.model.User;
import ru.trush.courses.repository.UserRepository;
import ru.trush.courses.service.UserService;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto dto) {
        User user = UserMapper.mapToUser(dto);
        user.setCourses(new HashSet<>());
        return UserMapper.mapToDto(userRepository.save(user));
    }
}
