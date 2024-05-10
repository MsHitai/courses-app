package ru.trush.courses.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.service.LessonService;

/**
 * Handles requests related to lessons.
 */
@RestController
@RequestMapping("/courses")
@Slf4j
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    /**
     * Creates a new lesson for the course.
     *
     * @param courseId the ID of the course
     * @param dto      the lesson data
     * @return {@link LessonDto} the created lesson
     */
    @PostMapping("/{courseId}/lessons")
    public LessonDto addLesson(@PathVariable Long courseId, @Valid @RequestBody LessonDto dto) {
        log.info("Received POST request to add a lesson {} to the course by id {}", dto.toString(), courseId);
        return lessonService.addLesson(courseId, dto);
    }

    /**
     * Updates an existing lesson for the course.
     *
     * @param courseId the ID of the course
     * @param lessonId the ID of the lesson
     * @param dto      the lesson data
     * @return {@link LessonDto} the updated lesson
     */
    @PutMapping("/{courseId}/lessons/{lessonId}")
    public LessonDto updateLesson(@PathVariable Long courseId,
                                  @PathVariable Long lessonId,
                                  @Valid @RequestBody LessonDto dto) {
        log.info("Received PUT request to update a lesson by id {} to the course by id {} with these changes {}",
                lessonId, courseId, dto.toString());
        return lessonService.updateLesson(courseId, lessonId, dto);
    }

    /**
     * Deletes an existing lesson for the course.
     *
     * @param courseId the ID of the course
     * @param lessonId the ID of the lesson
     */
    @DeleteMapping("/{courseId}/lessons/{lessonId}")
    public void deleteLesson(@PathVariable Long courseId, @PathVariable Long lessonId) {
        log.info("Received DELETE request to remove a lesson by id {} from the course by id {}", lessonId, courseId);
        lessonService.deleteLesson(courseId, lessonId);
    }
}
