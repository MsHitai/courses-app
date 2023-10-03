package ru.trush.courses.mapper;

import lombok.experimental.UtilityClass;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.model.User;

@UtilityClass
public class UserMapper {

    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }
}
