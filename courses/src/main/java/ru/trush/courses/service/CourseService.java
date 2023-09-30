package ru.trush.courses.service;

import ru.trush.courses.dto.CourseDto;

public interface CourseService {
    CourseDto assignUser(Long courseId, Long userId);

}
