package ru.trush.courses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.dto.CourseWithUsersDto;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.model.Course;
import ru.trush.courses.model.Lesson;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LessonMapper.class, UserMapper.class})
public interface CourseMapper {

    @Mapping(target = "users", ignore = true)
    Course mapToCourse(CourseDto dto, List<Lesson> lessons);

    CourseDto mapToDto(Course course, List<LessonDto> lessonDtos);

    CourseDto mapToDto(Course course);

    CourseWithUsersDto mapToDtoWithUsers(Course course, List<UserDto> users, List<LessonDto> lessonDtos);
}
