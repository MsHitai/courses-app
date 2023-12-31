package ru.trush.courses.service;

import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.dto.CourseWithUsersDto;

import java.util.List;

public interface CourseService {
    CourseDto assignUser(Long courseId, Long userId);

    CourseDto addCourse(CourseDto dto);

    CourseDto updateCourse(Long courseId, CourseDto dto);

    CourseWithUsersDto findCourseById(Long courseId);

    List<CourseDto> findAllCourses();
}
