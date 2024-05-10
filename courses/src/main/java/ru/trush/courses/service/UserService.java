package ru.trush.courses.service;

import ru.trush.courses.dto.UserDto;
import ru.trush.courses.model.User;

import java.util.List;

/**
 * Service for user
 */
public interface UserService {
    /**
     * Method adds a new user to the database
     *
     * @param dto {@link UserDto}
     * @return {@link UserDto}
     */
    UserDto addUser(UserDto dto);

    /**
     * Method finds a user by id
     *
     * @param id identification
     * @return {@link UserDto}
     */
    UserDto getUserById(Long id);

    /**
     * Method finds all users
     *
     * @return {@link List}&lt{@link UserDto}&gt
     */
    List<UserDto> findAllUsers();

    /**
     * Method updates user
     *
     * @param id  identification
     * @param dto {@link UserDto}
     * @return {@link User} updated user
     */
    UserDto updateUser(Long id, UserDto dto);
}
