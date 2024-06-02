package ru.trush.courses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

    @Mapping(target = "courses", ignore = true)
    User mapToUser(UserDto dto);
}
