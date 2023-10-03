package ru.trush.courses.service;

import ru.trush.courses.dto.LessonDto;

public interface LessonService {
    LessonDto addLesson(Long courseId, LessonDto dto);

    LessonDto updateLesson(Long courseId, Long lessonId, LessonDto dto);

    void deleteLesson(Long courseId, Long lessonId);
}
