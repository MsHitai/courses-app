package ru.trush.courses.mapper;

import lombok.experimental.UtilityClass;
import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.dto.CourseWithUsersDto;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.model.Course;
import ru.trush.courses.model.Lesson;

import java.util.List;

@UtilityClass
public class CourseMapper {

    public Course mapToCourse(CourseDto dto, List<Lesson> lessons) {
        return Course.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .lessons(lessons)
                .build();
    }

    public CourseDto mapToDto(Course course, List<LessonDto> lessonDtos) {
        return CourseDto.builder()
                .id(course.getId())
                .author(course.getAuthor())
                .title(course.getTitle())
                .lessons(lessonDtos)
                .build();
    }

    public CourseWithUsersDto mapToDtoWithUsers(Course course, List<UserDto> users, List<LessonDto> lessonDtos) {
        return CourseWithUsersDto.builder()
                .id(course.getId())
                .author(course.getAuthor())
                .title(course.getTitle())
                .lessons(lessonDtos)
                .users(users)
                .build();
    }
}
