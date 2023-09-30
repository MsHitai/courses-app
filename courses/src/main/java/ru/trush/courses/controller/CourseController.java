package ru.trush.courses.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.service.CourseService;

@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{courseId}/assign")
    public CourseDto assignUser(@PathVariable() Long courseId, @RequestParam("userId") Long userId) {
        log.info("Received POST request to assign user with id {} to the course by id {}", userId, courseId);
        return courseService.assignUser(courseId, userId);
    }
}
