package ru.trush.courses.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.exception.DataNotFoundException;
import ru.trush.courses.mapper.CourseMapper;
import ru.trush.courses.mapper.LessonMapper;
import ru.trush.courses.model.Course;
import ru.trush.courses.model.Lesson;
import ru.trush.courses.model.User;
import ru.trush.courses.repository.CourseRepository;
import ru.trush.courses.repository.UserRepository;
import ru.trush.courses.service.CourseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseDto assignUser(Long courseId, Long userId) {
        User user = checkUserId(userId);
        Course course = courseRepository.findByIdWithAllFields(courseId).orElseThrow(() -> new
                DataNotFoundException(String.format("Course with the id=%d is not in the database", courseId)));
        user.getCourses().add(course);
        course.getUsers().add(user);
        List<Lesson> lessons = course.getLessons();
        List<LessonDto> lessonDtos = lessons.stream().map(LessonMapper::mapToDto).toList();
        return CourseMapper.mapToDto(course, lessonDtos);
    }

    private User checkUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new
                DataNotFoundException(String.format("User with the id=%d is not in the database", userId)));
    }

    private Course checkCourseId(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new
                DataNotFoundException(String.format("Course with the id=%d is not in the database", courseId)));
    }

}
