package ru.trush.courses.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.dto.CourseWithUsersDto;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.dto.UserDto;
import ru.trush.courses.exception.DataNotFoundException;
import ru.trush.courses.mapper.CourseMapper;
import ru.trush.courses.mapper.LessonMapper;
import ru.trush.courses.mapper.UserMapper;
import ru.trush.courses.model.Course;
import ru.trush.courses.model.Lesson;
import ru.trush.courses.model.User;
import ru.trush.courses.repository.CourseRepository;
import ru.trush.courses.repository.UserRepository;
import ru.trush.courses.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseDto assignUser(Long courseId, Long userId) {
        User user = checkUserId(userId);
        Course course = checkFullCourse(courseId);
        user.getCourses().add(course);
        course.getUsers().add(user);
        courseRepository.save(course);
        List<Lesson> lessons = course.getLessons();
        List<LessonDto> lessonDtos = lessons.stream().map(LessonMapper::mapToDto).toList();
        return CourseMapper.mapToDto(course, lessonDtos);
    }

    @Override
    public CourseDto addCourse(CourseDto dto) {
        List<Lesson> lessons = dto.getLessons().stream().map(LessonMapper::mapToLesson).toList();
        Course course = CourseMapper.mapToCourse(dto, lessons);
        return CourseMapper.mapToDto(courseRepository.save(course), dto.getLessons());
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto dto) {
        checkCourseId(courseId);
        List<Lesson> lessons = dto.getLessons().stream().map(LessonMapper::mapToLesson).toList();
        Course course = CourseMapper.mapToCourse(dto, lessons);
        return CourseMapper.mapToDto(courseRepository.save(course), dto.getLessons());
    }

    @Override
    public CourseWithUsersDto findCourseById(Long courseId) {
        Course course = checkFullCourse(courseId);
        List<LessonDto> lessons = course.getLessons().stream().map(LessonMapper::mapToDto).toList();
        List<UserDto> users = course.getUsers().stream().map(UserMapper::mapToDto).toList();
        return CourseMapper.mapToDtoWithUsers(course, users, lessons);
    }

    @Override
    public List<CourseDto> findAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private User checkUserId(Long userId) {
        return userRepository.findByIdWithCourses(userId).orElseThrow(() -> new
                DataNotFoundException(String.format("User with the id=%d is not in the database", userId)));
    }

    private void checkCourseId(Long courseId) {
        courseRepository.findById(courseId).orElseThrow(() -> new
                DataNotFoundException(String.format("Course with the id=%d is not in the database", courseId)));
    }

    private Course checkFullCourse(Long courseId) {
        return courseRepository.findByIdWithAllFields(courseId).orElseThrow(() -> new
                DataNotFoundException(String.format("Course with the id=%d is not in the database", courseId)));
    }

}
