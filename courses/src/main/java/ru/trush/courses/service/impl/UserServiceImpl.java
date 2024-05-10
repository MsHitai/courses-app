package ru.trush.courses.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.exception.DataNotFoundException;
import ru.trush.courses.mapper.UserMapper;
import ru.trush.courses.model.User;
import ru.trush.courses.repository.UserRepository;
import ru.trush.courses.service.UserService;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto addUser(UserDto dto) {
        User user = UserMapper.mapToUser(dto);
        user.setCourses(new HashSet<>());
        return UserMapper.mapToDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        return UserMapper.mapToDto(getUserOrElseThrow(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapToDto)
                .toList();
    }

    /**
     * Method updates user
     *
     * @param id  identification
     * @param dto {@link UserDto}
     * @return {@link User} updated user
     */
    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto dto) {
        User user = getUserOrElseThrow(id);
        user.setUsername(dto.getUsername());
        return UserMapper.mapToDto(userRepository.save(user));
    }

    /**
     * Method finds a user by id and throws {@link DataNotFoundException} if user is not found
     *
     * @param id identification
     * @return {@link User} user
     */
    private User getUserOrElseThrow(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> {
                    log.error("User by id {} was not found in the database", id);
                    return new DataNotFoundException("User not found");
                }
        );
    }
}
