package ru.trush.courses.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.trush.courses.dto.CourseDto;
import ru.trush.courses.dto.CourseWithUsersDto;
import ru.trush.courses.service.CourseService;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping()
    public CourseDto addCourse(@Valid @RequestBody CourseDto dto) {
        log.info("Received POST request to add a course {}", dto.toString());
        checkLessons(dto);
        return courseService.addCourse(dto);
    }

    @PutMapping("/{courseId}")
    public CourseDto updateCourse(@PathVariable Long courseId, @Valid @RequestBody CourseDto dto) {
        log.info("Received PUT request to update a course by id {} with changes {}", courseId, dto.toString());
        checkLessons(dto);
        return courseService.updateCourse(courseId, dto);
    }

    @GetMapping("/{courseId}")
    public CourseWithUsersDto findCourseById(@PathVariable Long courseId) {
        log.info("Received GET request to find a course by id {}", courseId);
        return courseService.findCourseById(courseId);
    }

    @GetMapping()
    public List<CourseDto> findAllCourses() {
        log.info("Received GET request to find all courses");
        return courseService.findAllCourses();
    }

    private void checkLessons(CourseDto dto) {
        if (dto.getLessons() == null) {
            dto.setLessons(new ArrayList<>());
        }
    }
}
