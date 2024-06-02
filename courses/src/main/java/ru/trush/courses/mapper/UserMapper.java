package ru.trush.courses.mapper;

import org.mapstruct.Mapper;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

    default User mapToUser(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }
}
