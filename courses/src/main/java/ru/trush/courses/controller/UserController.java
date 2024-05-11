package ru.trush.courses.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.service.UserService;

import java.util.List;

/**
 * Handles requests related to users
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * Creates a new user from the provided DTO and saves it to the database.
     *
     * @param dto The user data transfer object containing information about the new user.
     * @return A DTO representation of the newly created user.
     */
    @PostMapping()
    public UserDto addUser(@Valid @RequestBody UserDto dto) {
        log.info("Received POST request to add a user {}", dto);
        return userService.addUser(dto);
    }

    /**
     * Retrieves a user by their ID from the database and returns it as a DTO.
     *
     * @param id The ID of the user to retrieve.
     * @return A DTO representation of the user with the specified ID, or null if no such user exists.
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        log.info("Received GET request to fetch a user by ID: {}", id);
        return userService.getUserById(id);
    }

    /**
     * Retrieves all users from the database and returns them as a list of DTOs.
     *
     * @return A list of DTO representations of all users in the database.
     */
    @GetMapping()
    public List<UserDto> getAllUsers() {
        log.info("Received GET request to fetch all users");
        return userService.findAllUsers();
    }

    /**
     * Updates an existing user with the provided ID and DTO data.
     *
     * @param id  The ID of the user to update.
     * @param dto The user data transfer object containing updated information about the user.
     * @return A DTO representation of the updated user.
     */
    @PutMapping("/{id}/update")
    public UserDto updateUser(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        log.info("Received PUT request to update a user: {}", id);
        return userService.updateUser(id, dto);
    }
}
