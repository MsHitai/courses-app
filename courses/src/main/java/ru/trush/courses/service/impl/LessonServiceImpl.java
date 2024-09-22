package ru.trush.courses.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.exception.DataNotFoundException;
import ru.trush.courses.mapper.LessonMapper;
import ru.trush.courses.model.Course;
import ru.trush.courses.model.Lesson;
import ru.trush.courses.repository.CourseRepository;
import ru.trush.courses.repository.LessonRepository;
import ru.trush.courses.service.LessonService;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;

    @Override
    @Transactional
    public LessonDto addLesson(Long courseId, LessonDto dto) {
        Course course = checkFullCourse(courseId);
        Lesson lesson = lessonMapper.mapToLesson(dto);
        lesson.setCourse(course);
        course.getLessons().add(lesson);
        return lessonMapper.mapToDto(lessonRepository.save(lesson));
    }

    @Override
    @Transactional
    public LessonDto updateLesson(Long courseId, Long lessonId, LessonDto dto) {
        Course course = checkCourseId(courseId);
        Lesson lesson = checkLessonId(lessonId);
        lesson.setText(dto.getText());
        lesson.setTitle(dto.getTitle());
        lesson.setCourse(course);
        return lessonMapper.mapToDto(lessonRepository.save(lesson));
    }

    @Override
    @Transactional
    public void deleteLesson(Long courseId, Long lessonId) {
        checkCourseId(courseId);
        checkLessonId(lessonId);
        lessonRepository.deleteById(lessonId);
    }

    private Course checkCourseId(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new
                DataNotFoundException(String.format("Course with the id=%d is not in the database", courseId)));
    }

    private Lesson checkLessonId(Long lessonId) {
        return lessonRepository.findById(lessonId).orElseThrow(() -> new
                DataNotFoundException(String.format("Lesson with the id=%d is not in the database", lessonId)));
    }

    private Course checkFullCourse(Long courseId) {
        return courseRepository.findByIdWithAllFields(courseId).orElseThrow(() -> new
                DataNotFoundException(String.format("Course with the id=%d is not in the database", courseId)));
    }
}
