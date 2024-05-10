package ru.trush.courses.service;

import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.dto.CourseWithUsersDto;

import java.util.List;

public interface CourseService {
    /**
     * Assigns a user to a course.
     *
     * @param courseId the course id
     * @param userId   the user id
     * @return the course
     */
    CourseDto assignUser(Long courseId, Long userId);

    /**
     * Creates a new course.
     *
     * @param dto the course data
     * @return {@link CourseDto} the created course
     */
    CourseDto addCourse(CourseDto dto);

    /**
     * Updates a course.
     *
     * @param courseId the course id
     * @param dto      the course data
     * @return {@link CourseDto} the updated course
     */
    CourseDto updateCourse(Long courseId, CourseDto dto);

    /**
     * Finds a course by id.
     *
     * @param courseId the course id
     * @return {@link CourseDto} the course
     */
    CourseWithUsersDto findCourseById(Long courseId);

    /**
     * Finds all courses.
     *
     * @return {@link List<CourseDto>} the list of courses
     */
    List<CourseDto> findAllCourses();
}
