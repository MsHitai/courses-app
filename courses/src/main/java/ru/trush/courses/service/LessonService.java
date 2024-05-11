package ru.trush.courses.service;

import ru.trush.courses.dto.LessonDto;

public interface LessonService {
    /**
     * Creates a lesson.
     *
     * @param courseId id of the course
     * @param dto      the lesson data
     * @return {@link LessonDto} the lesson
     */
    LessonDto addLesson(Long courseId, LessonDto dto);

    /**
     * Updates a lesson.
     *
     * @param courseId id of the course
     * @param lessonId id of the lesson
     * @param dto      the lesson data
     */
    LessonDto updateLesson(Long courseId, Long lessonId, LessonDto dto);

    /**
     * Deletes a lesson.
     *
     * @param courseId id of the course
     * @param lessonId id of the lesson
     */
    void deleteLesson(Long courseId, Long lessonId);
}
